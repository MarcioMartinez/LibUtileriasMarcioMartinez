package com.dev.marciomartinez.libutileriasmarciomartinez;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.math.BigDecimal;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UTILERIAS {

    public static void mostrarMensajeNormal(Context context, String titulo, String mensaje, boolean cancelable, int icono) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(titulo);
        alertDialog.setCancelable(cancelable);
        if(icono != 0) {
            alertDialog.setIcon(icono);
        }
        alertDialog.setMessage(mensaje);
        alertDialog.setNegativeButton("OK",null);
        AlertDialog alert = alertDialog.create();
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//Añadirmos este permiso <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
        alert.show();
    }

    public static void mostrarMensajeDecision(final Context context, String titulo, String mensaje, boolean cancelable, int icono) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(titulo);
        if(icono != 0) {
            alertDialog.setIcon(icono);
        }
        alertDialog.setMessage(mensaje);
        alertDialog.setCancelable(cancelable);
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Activity) context).finish();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alert.show();
    }

    public static void mostrarMensajeToast(Context context, String mensaje, int tiempo) {
        Toast.makeText(context,mensaje,tiempo).show();
    }


    public static ProgressDialog mostrarProgressDialog(Context context, String titulo, String mensaje, boolean cancelable, int icono) {
        ProgressDialog pd;
        pd = new ProgressDialog(context);
        pd.setMessage(mensaje);
        pd.setTitle(titulo);
        if(icono != 0){
            pd.setIcon(icono);
        }
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(cancelable);
        pd.show();
        return pd;
    }

    public static double redondearDouble(double numero){
        return Math.round(numero * 100.0) / 100.0;
    }

    public static double redondear(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String obtenerMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }


    public static double redondear2(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public static String convertirNumeroAString(double numero){
        return String.valueOf(numero);
    }

    public static double convertirStringADouble(String numero){
        return Double.parseDouble(numero);
    }

    public static double convertirStringAInt(String numero){
        return Integer.parseInt(numero);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static String formatoMoneda(double numero){
        return NumberFormat.getCurrencyInstance().format(numero);
    }

    public static String obtenerFechaYHoraActual(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static String obtenerFechaActual(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    public static String obtenerHoraActual(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public static void ocultarTeclado(@NonNull Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        //Lo vamos a llamar asi MODULO.ocultarTeclado(MainActivity.this);
    }

    public static void ocultarTecladoForzado(@NonNull Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public static void ocultarTecladoSegundaForma(Context context ) {
        InputMethodManager inputManager = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void ocultarTecladoAutoCompleteTextView(Context context, int id) {
        AutoCompleteTextView txtView = (AutoCompleteTextView) ((Activity)context).findViewById(id);
        InputMethodManager im = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(txtView.getWindowToken(), 0);
    }

    public static void ocultarTecladoEditText(Context context, int id) {
        EditText txtView = (EditText) ((Activity)context).findViewById(id);
        InputMethodManager im = (InputMethodManager) ((Activity) context).getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(txtView.getWindowToken(), 0);
    }

    public static void ocoplarListViewAScrollView(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static boolean saberSiHayInternet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
