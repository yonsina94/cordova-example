package com.anatsoft.example;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class cordovaExample extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try{
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if(action.equals("echo")){
            JSONObject obj = args.getJSONObject(0);
            String message = obj.getString("message");
            if (!message.equals("")) {
                callbackContext.success(message);
            } else {
                callbackContext.error("Mensaje Vacio !");
            }
        } else if(action.equals("calculate")){
            JSONObject obj = args.getJSONObject(0);
            String operation = obj.getString("operacion");
            int val1 = obj.getInteger("val1");
            int val2 = obj.getInteger("val2");
            this.calculate(operation, val1, val2, callbackContext);
        }
        return true;
        } catch(JSONException e){
            return false;
        }
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void calculate(String operation,int val1, int val2,CallbackContext callback){
        if(operation != null){
            switch (operation) {
                case "suma":
                    double result = val1 + val2;
                    callback.success(result.toString());
                    break;
                case "resta":
                    double result = val1 - val2;
                    callback.success(result.toString());
                break;

                case "multiplicacion":
                    double result = val1 * val2;
                    callback.success(result.toString());
                break;

                case "division":
                    double result = val1 / val2;
                    callback.success(result.toString());
                break;

                default:
                callback.error("Este calculo no esta adminitido aun !");
                    break;
            }
        } else{
            callback.error("Debe de ingresar la operacion que usted va a hacer antes de continuar !");
        }
    }
}
