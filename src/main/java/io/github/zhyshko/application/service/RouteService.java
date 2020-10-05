package io.github.zhyshko.application.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import io.github.zhyshko.application.dto.route.ApiRoute;
import io.github.zhyshko.application.dto.route.RoutesApiResponse;
import io.github.zhyshko.application.entity.Coordinates;
import io.github.zhyshko.application.entity.Route;
import io.github.zhyshko.application.exception.RouteNotFoundException;

@Service
public class RouteService {

	private String apiKey;
	private String queryString;
	private Gson gson = new Gson();

	public RouteService() {
		Properties properties = new Properties();
		try {
			properties.load(RouteService.class.getClassLoader().getResourceAsStream("application.properties"));
			this.apiKey = properties.getProperty("mapbox.apikey");
			this.queryString = "https://api.mapbox.com/directions/v5/mapbox/driving?access_token=" + apiKey;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Optional<Route> tryGetRoute(Coordinates departurePoint, Coordinates destinationPoint) {
		HttpEntity apiResponse = queryApi(departurePoint, destinationPoint)
				.orElseThrow(() -> new NullPointerException("Could not query MapBox api"));
		String responseString = parseEntityToString(apiResponse)
				.orElseThrow(() -> new NullPointerException("Could not parse entity to string"));
		RoutesApiResponse responseObject = gson.fromJson(responseString, RoutesApiResponse.class);
		ApiRoute shortestRoute = findShortestApiRoute(responseObject.getRoutes())
				.orElseThrow(() -> new RouteNotFoundException("Could not find shortest route"));
		Route result = buildRoute(departurePoint, destinationPoint, shortestRoute);
		return Optional.of(result);
	}

	private Optional<HttpEntity> queryApi(Coordinates departurePoint, Coordinates destinationPoint) {
		String coordinatesString = buildCoordinatesString(departurePoint, destinationPoint);
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(queryString);
			httpPost.setEntity(getUrlEncodedFormEntity(coordinatesString).orElseThrow(()->new NullPointerException("Could not create url encoded parameter")));
			CloseableHttpResponse response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return Optional.of(entity);
		} catch (IOException e1) {
			e1.printStackTrace();
			return Optional.empty();
		}
	}

	private String buildCoordinatesString(Coordinates departurePoint, Coordinates destinationPoint) {
		String departureLatitude = departurePoint.getLatitude();
		String departureLongitude = departurePoint.getLongitude();
		String destinationLatitude = destinationPoint.getLatitude();
		String destinationLongitude = destinationPoint.getLongitude();
		StringBuilder queryParamBuilder = new StringBuilder();
		queryParamBuilder.append(departureLongitude).append(",").append(departureLatitude).append(";")
				.append(destinationLongitude).append(",").append(destinationLatitude);
		return queryParamBuilder.toString();
	}

	private Optional<UrlEncodedFormEntity> getUrlEncodedFormEntity(String paramValue) {
		try {
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("coordinates", paramValue));
			return Optional.of(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e) {
			return Optional.empty();
		}
	}

	private Optional<String> parseEntityToString(HttpEntity entity) {
		try {
			return Optional.of(EntityUtils.toString(entity, "UTF-8"));
		} catch (IOException e) {
			return Optional.empty();
		}
	}

	private Optional<ApiRoute> findShortestApiRoute(List<ApiRoute> routes) {
		return routes.stream().sorted((o1, o2) -> Integer.compare((int) o1.getDuration(), (int) o1.getDuration()))
				.findFirst();
	}

	private Route buildRoute(Coordinates departurePoint, Coordinates destinationPoint, ApiRoute route) {
		return Route.builder().distance(route.getDistance() / 1000).time((int) route.getDuration() / 60)
				.departure(departurePoint).destination(destinationPoint).build();
	}

}
