package io.github.zhyshko.application.dto.route;

public class ApiRoute {

	private float duration;
	private float distance;
	
	
	public ApiRoute(float duration, float distance) {
		this.duration = duration;
		this.distance = distance;
	}


	public float getDuration() {
		return duration;
	}


	public float getDistance() {
		return distance;
	}
	
	
	
	
	
}
