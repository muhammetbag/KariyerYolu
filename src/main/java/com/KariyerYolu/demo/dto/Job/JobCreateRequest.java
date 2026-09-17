package com.KariyerYolu.demo.dto.Job;

public record JobCreateRequest(
String title, String description, String location, Long employerId
) {
}