package io.github.zhyshko.application.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.github.zhyshko.application.dto.route.RoutesApiResponse;
import io.github.zhyshko.application.entity.Coordinates;
import io.github.zhyshko.application.entity.Route;

@Service
public class RouteService {

	private String apiKey;

	private Gson gson = new Gson();
	
	public RouteService() {
		Properties properties = new Properties();
		try {
			properties.load(RouteService.class.getClassLoader().getResourceAsStream("application.properties"));
			this.apiKey = properties.getProperty("mapbox.apikey");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Optional<Route> tryGetRoute(Coordinates departurePoint, Coordinates destinationPoint) {
		String departureLatitude = departurePoint.getLatitude();
		String departureLongitude = departurePoint.getLongitude();
		String destinationLatitude = destinationPoint.getLatitude();
		String destinationLongitude = destinationPoint.getLongitude();
		String queryString = "https://api.mapbox.com/directions/v5/mapbox/driving?access_token=" + apiKey;
		StringBuilder queryBodyBuilder = new StringBuilder();
		queryBodyBuilder.append(departureLongitude).append(",").append(departureLatitude).append(";")
				.append(destinationLongitude).append(",").append(destinationLatitude);
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(queryString);
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("coordinates", queryBodyBuilder.toString()));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			RoutesApiResponse responseObject = gson.fromJson(responseString, RoutesApiResponse.class);
			responseObject.getRoutes()
					.sort((o1, o2) -> Integer.compare((int) o1.getDuration(), (int) o1.getDuration()));
			Route result = Route.builder()
					.distance(responseObject.getRoutes().get(0).getDistance() / 1000)
					.time((int) responseObject.getRoutes().get(0).getDuration() / 60)
					.departure(departurePoint)
					.destination(destinationPoint)
					.build();
			return Optional.of(result);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return Optional.empty();
	}
	
}
