package com.insurance.policy.model;

import java.util.List;

public record PolicyObject(String name, List<SubObject> subObjects) {

}
