package com.eric.larroque.tutorialcache.core;

import java.io.Serializable;

public record Product(Long id, String name, String description) implements Serializable {
}
