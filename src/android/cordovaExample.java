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
            String operation = obj.getString("operation");
            int val1 = obj.getFloat("val1");
            int val2 = obj.getFloat("val2");
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

    private String toStr(double result){
        Double r = new Double(result);
        return r.toString();
    }

    private void calculate(String operation,float val1, float val2,CallbackContext callback){
        double reslt;
        if(operation != null){
            switch (operation) {
                case "suma":
                    reslt =(val1 + val2);
                    callback.success(toStr(reslt));
                    break;
                case "resta":
                    reslt =(val1 - val2);
                    callback.success(toStr(reslt));
                break;

                case "multiplicacion":
                    reslt =(val1 * val2);
                    callback.success(toStr(reslt));
                break;

                case "division":
                    reslt =(val1 / val2);
                    callback.success(toStr(reslt));
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
