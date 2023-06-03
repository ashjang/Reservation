package com.ashjang.domain.location;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LocationProvider {

    public Location getLocation(String apiKey, String address) {
        // 주소를 URL 인코딩
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);

        // API 호출 URL 생성
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                encodedAddress +
                "&key=" + apiKey;

        // RestTemplate을 사용하여 API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, URI.create(apiUrl));
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(requestEntity, JsonNode.class);
        JsonNode responseJson = responseEntity.getBody();

        double latitude = responseJson.get("results").get(0).get("geometry").get("location").get("lat").asDouble();
        double longitude = responseJson.get("results").get(0).get("geometry").get("location").get("lng").asDouble();

        return new Location(latitude, longitude);
    }

    public double getDistance(double lat, double lng, double myLat, double myLng) {
        // 지구 반지름
        double earthRadius = 6371;

        // 위도 및 경도를 라디안 단위로 변환
        double latRad = Math.toRadians(lat);
        double lngRad = Math.toRadians(lng);

        // 위도 및 경도 차이 계산
        double latDiff = latRad - myLat;
        double lngDiff = lngRad - myLng;

        double a = Math.pow(Math.sin(latDiff / 2), 2) +
                Math.cos(latRad) * Math.cos(latRad) *
                        Math.pow(Math.sin(lngDiff / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance;
    }
}
