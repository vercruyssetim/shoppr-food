package com.switchfully.shoppr.recipe;

import javax.persistence.*;

@Entity
@Table(name = "INSTRUCTION")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instruction_seq")
    @SequenceGenerator(sequenceName = "instruction_seq", name = "instruction_seq", initialValue = 1, allocationSize = 1)
    private long id;

    private String description;

    private Instruction() {
    }

    public Instruction(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
