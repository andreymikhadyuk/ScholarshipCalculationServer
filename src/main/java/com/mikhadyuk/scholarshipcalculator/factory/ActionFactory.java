package com.mikhadyuk.scholarshipcalculator.factory;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.action.impl.LoginAction;
import com.mikhadyuk.scholarshipcalculator.action.impl.RegistrationAction;
import com.mikhadyuk.scholarshipcalculator.connection.ActionType;
import com.mikhadyuk.scholarshipcalculator.exception.ActionTypeNotFoundException;
import com.mikhadyuk.scholarshipcalculator.util.SingletonUtil;

public class ActionFactory {
    public Action createAction(ActionType actionType) throws ActionTypeNotFoundException {
        Action action;
        switch (actionType) {
            case LOGIN:
                action = SingletonUtil.getInstance(LoginAction.class);
                break;
            case REGISTRATION:
                action = SingletonUtil.getInstance(RegistrationAction.class);
                break;
            default:
                throw new ActionTypeNotFoundException();
        }
        return action;
    }
}
