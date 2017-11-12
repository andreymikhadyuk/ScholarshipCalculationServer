package com.mikhadyuk.scholarshipcalculator.action;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Action {
    void doAction(ObjectOutputStream outputStream, ObjectInputStream inputStream);
}
