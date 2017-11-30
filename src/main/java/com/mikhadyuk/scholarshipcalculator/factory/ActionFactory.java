package com.mikhadyuk.scholarshipcalculator.factory;

import com.mikhadyuk.scholarshipcalculator.action.Action;
import com.mikhadyuk.scholarshipcalculator.action.impl.*;
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
            case GETTIN_LIST_OF_DATA:
                action = SingletonUtil.getInstance(GettingListOfDataAction.class);
                break;
            case SAVE_DATA:
                action = SingletonUtil.getInstance(SaveDataAction.class);
                break;
            case UPDATE_DATA:
                action = SingletonUtil.getInstance(UpdateDataAction.class);
                break;
            case DELETE_DATA:
                action = SingletonUtil.getInstance(DeleteDataAction.class);
                break;
            case REPORT_CREATING:
                action = SingletonUtil.getInstance(ReportCreatingAction.class);
                break;
            default:
                throw new ActionTypeNotFoundException();
        }
        return action;
    }
}
