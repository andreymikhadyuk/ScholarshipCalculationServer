package com.mikhadyuk.scholarshipcalculator.factory;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.action.impl.LoginAction;
import com.mikhadyuk.scholarshipcalculator.connection.ActionType;
import com.mikhadyuk.scholarshipcalculator.exception.ActionTypeNotFoundException;

public class ActionFactory {
    public Action createAction(ActionType actionType) throws ActionTypeNotFoundException {
        Action action;
        switch (actionType) {
            case LOGIN:
                action = new LoginAction();
                break;
            default:
                throw new ActionTypeNotFoundException();
        }
        return action;
    }
}
