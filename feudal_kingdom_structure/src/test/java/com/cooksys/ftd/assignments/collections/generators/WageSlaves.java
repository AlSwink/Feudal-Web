package com.cooksys.ftd.assignments.collections.generators;

import com.cooksys.ftd.assignments.collections.model.Peon;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class WageSlaves extends Generator<Peon> implements CapitalistGeneration {
    private Cap cap;
    private Slave slave;

    public WageSlaves() {
        super(Peon.class);
    }

    public Peon generate(SourceOfRandomness random, GenerationStatus status) {
        int depth = cap != null ? cap.depth() : slave != null ? slave.depth() : -1;
        return generateWageSlave(random, depth >= 0 ? depth : status.size());
    }

    public void configure(Cap cap) {
        this.cap = cap;
    }

    public void configure(Slave slave) {
        this.slave = slave;
    }
}
