package com.filter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toolbar;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.filter.utility.Helper;
import com.filter.utility.TransformImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class ControlActivity extends Activity {


    static
    {
        System.loadLibrary("NativeImageProcessor");
    }
    Toolbar tb;
    ImageView imgtick;
    ImageView imgcancel;
    ImageView imgcenter;
    ImageView imgfirst;
    ImageView imgsecond;
    ImageView imgthird;
    ImageView imgfourth;
    SeekBar seekBar;
    Uri selectedImageUri;
    int currentFilter;


    final static int PICK_IMAGE=2;
    final static int MY_PERMISSION_REQUEST_STORAGE_PERMISSION=3;
    private final static String TAG= ControlActivity.class.getSimpleName();
    TransformImage transformImage;
    int screenWidth,screenHeight;

    Target mApplySingleFilterTarget=new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            int currentFilterValue=seekBar.getProgress();
            if(currentFilter==TransformImage.FILTER_BRIGHTNESS){
                transformImage.applyBrightnessSubFilter(currentFilterValue);
                Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_BRIGHTNESS),transformImage.getmBitmap(TransformImage.FILTER_BRIGHTNESS));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_BRIGHTNESS)));

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_BRIGHTNESS))).resize(0,screenHeight/2).into(imgcenter);

            }else if (currentFilter==TransformImage.FILTER_SATURATION){
                transformImage.applySaturationSubFilter(currentFilterValue);
                Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_SATURATION),transformImage.getmBitmap(TransformImage.FILTER_SATURATION));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_SATURATION)));

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_SATURATION))).resize(0,screenHeight/2).into(imgcenter);


            }else if (currentFilter==TransformImage.FILTER_VIGNETTE){
                transformImage.applyVignetteSubFilter(currentFilterValue);
                Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_VIGNETTE),transformImage.getmBitmap(TransformImage.FILTER_VIGNETTE));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_VIGNETTE)));

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_VIGNETTE))).resize(0,screenHeight/2).into(imgcenter);


            }else if (currentFilter==TransformImage.FILTER_CONTRAST){
                transformImage.applyContrastSubFilter(currentFilterValue);
                Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_CONTRAST),transformImage.getmBitmap(TransformImage.FILTER_CONTRAST));
                Picasso.with(ControlActivity.this).invalidate(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_CONTRAST)));

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_CONTRAST))).resize(0,screenHeight/2).into(imgcenter);


            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    Target msmallTarget=new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            transformImage=new TransformImage(ControlActivity.this,bitmap);
            transformImage.applyBrightnessSubFilter(TransformImage.DEFAULT_BRIGHTNESS);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_BRIGHTNESS),transformImage.getmBitmap(TransformImage.FILTER_BRIGHTNESS));

            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_BRIGHTNESS))).fit().centerInside().into(imgfirst);

            //
            transformImage.applySaturationSubFilter(TransformImage.DEFAULT_SATURATION);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_SATURATION),transformImage.getmBitmap(TransformImage.FILTER_SATURATION));

            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_SATURATION))).fit().centerInside().into(imgsecond);

            //
            transformImage.applyVignetteSubFilter(TransformImage.DEFAULT_VIGNETTE);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_VIGNETTE),transformImage.getmBitmap(TransformImage.FILTER_VIGNETTE));

            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_VIGNETTE))).fit().centerInside().into(imgthird);

            //
            transformImage.applyContrastSubFilter(TransformImage.DEFAULT_CONTRAST);

            Helper.writeDataIntoExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_CONTRAST),transformImage.getmBitmap(TransformImage.FILTER_CONTRAST));

            Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_CONTRAST))).fit().centerInside().into(imgfourth);


        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
//        tb= (Toolbar) findViewById(R.id.toolbar);
//        tb.setTitle(getText(R.string.app_name));
//        tb.setNavigationIcon(R.drawable.icon);
//        tb.setTitleTextColor(getColor(R.color.white));

        imgtick=(ImageView) findViewById(R.id.imageView3);
        imgcenter=(ImageView) findViewById(R.id.center);
        imgfirst=(ImageView) findViewById(R.id.imageView4);
        imgsecond=(ImageView) findViewById(R.id.imageView5);
        imgthird=(ImageView) findViewById(R.id.imageView6);
        imgfourth=(ImageView) findViewById(R.id.imageView7);
        seekBar=(SeekBar)findViewById(R.id.seekBar);


        imgtick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ControlActivity.this,ImagePreviewActivity.class);
                startActivity(intent);
            }
        });

        imgcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestStoragePermissions();
                if(ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                return;
                }

                    Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);

            }
        });

        imgfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setMax(TransformImage.MAX_BRIGHTNESS);
                seekBar.setProgress(TransformImage.DEFAULT_BRIGHTNESS);

                currentFilter=TransformImage.FILTER_BRIGHTNESS;
                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_BRIGHTNESS))).resize(0,screenHeight/2).into(imgcenter);

            }
        });
        imgsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setMax(TransformImage.MAX_SATURATION);
                seekBar.setProgress(TransformImage.DEFAULT_SATURATION);

                currentFilter=TransformImage.FILTER_SATURATION;

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_SATURATION))).resize(0,screenHeight/2).into(imgcenter);


            }
        });
        imgthird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setMax(TransformImage.MAX_VIGNETTE);
                seekBar.setProgress(TransformImage.DEFAULT_VIGNETTE);

                currentFilter=TransformImage.FILTER_VIGNETTE;

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_VIGNETTE))).resize(0,screenHeight/2).into(imgcenter);


            }
        });
        imgfourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setMax(TransformImage.MAX_CONTRAST);
                seekBar.setProgress(TransformImage.DEFAULT_CONTRAST);

                currentFilter=TransformImage.FILTER_CONTRAST;

                Picasso.with(ControlActivity.this).load(Helper.getFileFromExternalStorage(ControlActivity.this,transformImage.getFileName(TransformImage.FILTER_CONTRAST))).resize(0,screenHeight/2).into(imgcenter);


            }
        });

        imgtick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(ControlActivity.this).load(selectedImageUri).into(mApplySingleFilterTarget);

            }
        });

        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight=displayMetrics.heightPixels;
        screenWidth=displayMetrics.widthPixels;

    }

    public void onRequestPermissionResult(int requestCode, String permission[], int[] grantResults){
        switch (requestCode){
            case MY_PERMISSION_REQUEST_STORAGE_PERMISSION:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    new MaterialDialog.Builder(ControlActivity.this).title(R.string.permission_title).content(R.string.permission_granted_content).positiveText("Ok").canceledOnTouchOutside(true).show();

                }
                else {
                    Log.d(TAG,"Permission Denied!");
                }
        }

    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){

        if(requestCode==PICK_IMAGE && resultCode == Activity.RESULT_OK){
            selectedImageUri = data.getData();
            Picasso.with(ControlActivity.this).load(selectedImageUri).fit().centerInside().into(imgcenter);

            Picasso.with(ControlActivity.this).load(selectedImageUri).into(msmallTarget);

            //Picasso.with(ControlActivity.this).load(selectedImageUri).fit().centerInside().into(imgfirst);
            Picasso.with(ControlActivity.this).load(selectedImageUri).fit().centerInside().into(imgsecond);
            Picasso.with(ControlActivity.this).load(selectedImageUri).fit().centerInside().into(imgthird);
            Picasso.with(ControlActivity.this).load(selectedImageUri).fit().centerInside().into(imgfourth);

        }

    }

    public void requestStoragePermissions(){

        if(ContextCompat.checkSelfPermission(ControlActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(ControlActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                new MaterialDialog.Builder(ControlActivity.this).title(R.string.permission_title).content(R.string.permission_content).negativeText(R.string.permission_cancel).positiveText(R.string.permission_agree_settings).canceledOnTouchOutside(true).onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS),0);
                    }
                }).show();

            }else {
                ActivityCompat.requestPermissions(ControlActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST_STORAGE_PERMISSION);
            }
            return;

        }


    }


}



