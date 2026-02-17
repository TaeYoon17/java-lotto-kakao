package model.interfaces;

import model.entities.Ticket;

import java.util.List;

public interface INumberGenerator {
    Ticket generate(int count, List<Integer> range);
}
