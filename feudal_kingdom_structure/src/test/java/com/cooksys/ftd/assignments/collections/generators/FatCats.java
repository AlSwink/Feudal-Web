package com.cooksys.ftd.assignments.collections.generators;

import com.cooksys.ftd.assignments.collections.model.Lord;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class FatCats extends Generator<Lord> implements CapitalistGeneration {
    private Cap cap;
    private Cat cat;

    public FatCats() {
        super(Lord.class);
    }

    public Lord generate(SourceOfRandomness random, GenerationStatus status) {
        int depth = cap != null ? cap.depth() : cat != null ? cat.depth() : -1;
        return generateFatCat(random, depth >= 0 ? depth : status.size());
    }

    public void configure(Cap cap) {
        this.cap = cap;
    }

    public void configure(Cat cat) {
        this.cat = cat;
    }
}
