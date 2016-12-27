package com.cooksys.ftd.assignments.collections.generators;

import com.cooksys.ftd.assignments.collections.model.Feudal;
import com.cooksys.ftd.assignments.collections.model.Lord;
import com.cooksys.ftd.assignments.collections.model.Peon;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.HashSet;
import java.util.Set;

public interface CapitalistGeneration {

    default Feudal generateCapitalist(SourceOfRandomness random) {
        return generateCapitalist(random, 0);
    }

    default Feudal generateCapitalist(SourceOfRandomness random, int depth) {
        return generateCapitalist(random, depth, null);
    }

    default Feudal generateCapitalist(SourceOfRandomness random, int depth, Lord root) {
        return generateCapitalist(random, generateFatCat(random, depth, root));
    }

    default Feudal generateCapitalist(SourceOfRandomness random, Lord parent) {
        if (random.nextBoolean()) {
            return generateFatCat(random, parent);
        } else {
            return generateWageSlave(random, parent);
        }
    }

    default Set<Feudal> generateCapitalists(SourceOfRandomness random, int count) {
        return generateCapitalists(random, count, 0);
    }

    default Set<Feudal> generateCapitalists(SourceOfRandomness random, int count, int depth) {
        return generateCapitalists(random, count, depth, (Lord) null);
    }

    default Set<Feudal> generateCapitalists(SourceOfRandomness random, int count, int depth, Lord root) {
        Set<Lord> roots = new HashSet<>();
        roots.add(root);
        return generateCapitalists(random, count, roots);
    }

    default Set<Feudal> generateCapitalists(SourceOfRandomness random, int count, int depth, Set<Lord> roots) {
        Set<Lord> parents = new HashSet<>();
        for (Lord root : roots) {
            parents.add(generateFatCat(random, depth, root));
        }
        return generateCapitalists(random, count, parents);
    }

    default Set<Feudal> generateCapitalists(SourceOfRandomness random, int count, Lord parent) {
        Set<Lord> parents = new HashSet<>();
        parents.add(parent);
        return generateCapitalists(random, count, parents);
    }

    default Set<Feudal> generateCapitalists(SourceOfRandomness random, int count, Set<Lord> parents) {
        Set<Feudal> result = new HashSet<>();
        for (int i = 0; i < count; i++) {
            result.add(generateCapitalist(random, random.choose(parents)));
        }
        return result;
    }

    default Lord generateFatCat(SourceOfRandomness random) {
        return generateFatCat(random, 0);
    }

    default Lord generateFatCat(SourceOfRandomness random, int depth) {
        return generateFatCat(random, depth, null);
    }

    default Lord generateFatCat(SourceOfRandomness random, int depth, Lord root) {
        return generateFatCat(random, depth > 0 ? generateFatCat(random, depth - 1, root) : root);
    }

    default Lord generateFatCat(SourceOfRandomness random, Lord parent) {
        String name = String.format("slave-%s", random.nextInt());
        int salary = random.nextInt((int) 1e2, (int) 1e4);
        return parent != null ? new Lord(name, salary, parent) : new Lord(name, salary);
    }

    default Set<Lord> generateFatCats(SourceOfRandomness random, int count) {
        return generateFatCats(random, count, 0);
    }

    default Set<Lord> generateFatCats(SourceOfRandomness random, int count, int depth) {
        return generateFatCats(random, count, depth, (Lord) null);
    }

    default Set<Lord> generateFatCats(SourceOfRandomness random, int count, int depth, Lord root) {
        Set<Lord> roots = new HashSet<>();
        roots.add(root);
        return generateFatCats(random, count, depth, roots);
    }

    default Set<Lord> generateFatCats(SourceOfRandomness random, int count, int depth, Set<Lord> roots) {
        Set<Lord> parents = new HashSet<>();
        for (Lord root : roots) {
            parents.add(generateFatCat(random, depth, root));
        }
        return generateFatCats(random, count, parents);
    }

    default Set<Lord> generateFatCats(SourceOfRandomness random, int count, Lord parent) {
        Set<Lord> parents = new HashSet<>();
        parents.add(parent);
        return generateFatCats(random, count, parents);
    }

    default Set<Lord> generateFatCats(SourceOfRandomness random, int count, Set<Lord> parents) {
        Set<Lord> result = new HashSet<>();
        for (int i = 0; i < count; i++) {
            result.add(generateFatCat(random, random.choose(parents)));
        }
        return result;
    }

    default Peon generateWageSlave(SourceOfRandomness random) {
        return generateWageSlave(random, 0);
    }

    default Peon generateWageSlave(SourceOfRandomness random, int depth) {
        return generateWageSlave(random, depth, null);
    }

    default Peon generateWageSlave(SourceOfRandomness random, int depth, Lord root) {
        return generateWageSlave(random, depth > 0 ? generateFatCat(random, depth - 1, root) : root);
    }

    default Peon generateWageSlave(SourceOfRandomness random, Lord parent) {
        String name = String.format("slave-%s", random.nextInt());
        int salary = random.nextInt((int) 1e2, (int) 1e4);
        return parent != null ? new Peon(name, salary, parent) : new Peon(name, salary);
    }

    default Set<Peon> generateWageSlaves(SourceOfRandomness random, int count) {
        return generateWageSlaves(random, count, 0);
    }

    default Set<Peon> generateWageSlaves(SourceOfRandomness random, int count, int depth) {
        return generateWageSlaves(random, count, depth, (Lord) null);
    }

    default Set<Peon> generateWageSlaves(SourceOfRandomness random, int count, int depth, Lord root) {
        Set<Lord> roots = new HashSet<>();
        roots.add(root);
        return generateWageSlaves(random, count, depth, roots);
    }

    default Set<Peon> generateWageSlaves(SourceOfRandomness random, int count, int depth, Set<Lord> roots) {
        Set<Lord> parents = new HashSet<>();
        for (Lord root : roots) {
            parents.add(generateFatCat(random, depth, root));
        }
        return generateWageSlaves(random, count, parents);
    }

    default Set<Peon> generateWageSlaves(SourceOfRandomness random, int count, Lord parent) {
        Set<Lord> parents = new HashSet<>();
        parents.add(parent);
        return generateWageSlaves(random, count, parents);
    }

    default Set<Peon> generateWageSlaves(SourceOfRandomness random, int count, Set<Lord> parents) {
        Set<Peon> result = new HashSet<>();
        for (int i = 0; i < count; i++) {
            result.add(generateWageSlave(random, random.choose(parents)));
        }
        return result;
    }
}
