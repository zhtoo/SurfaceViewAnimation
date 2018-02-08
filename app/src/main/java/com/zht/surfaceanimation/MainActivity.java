package com.zht.surfaceanimation;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zht.silkyanimation.SilkyAnimation;

public class MainActivity extends AppCompatActivity {


    private SilkyAnimation silkyAnimation;
    private Button btnStartAssets;
    private Button btnStartFile;
    private Button btnStop;
    private SurfaceView mSurfaceView;
    private ImageView iv_main;
    /**
     * your file dir
     */
    private String fileDir = "./app/src/main/assets/crow";
    private String assetsPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        silkyAnimation = new SilkyAnimation.Builder(mSurfaceView)
                .setCacheCount(5)
                .setFrameInterval(80)
                .setScaleType(SilkyAnimation.SCALE_TYPE_FIT_XY)
                .build();
        //如果需要在onCreate方法中直接调用开始，通过handler.postDelayed添加一个时延
        //if you call start() in onCreate method,use handler.postDelayed add a time delay to call it.
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        silkyAnimation.start("huabanyu");
                    }
                }, 40);
        //从文件读取 file resources
//        final File file ;
//
//        File externalStorageFile = Environment.getExternalStorageDirectory();
//        if(externalStorageFile == null){
//            file = new File(fileDir);
//        }else {
//            file = new File(Environment.getExternalStorageDirectory(), fileDir);
//        }

        //初始化完成之后直接就调用start传入file或者asset资源目录播放了
//        File file=new FIle(Environment.getExternalStorageDirectory() + File.separator+ "bird")
//        silkyAnimation.start(file);
        //或者
        assetsPath = "./app/src/main/assets/crow";
       // silkyAnimation.start(assetsPath);


        btnStartFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silkyAnimation.start(assetsPath);
            }
        });


//        btnStartFile.setOnClickListener(view -> silkyAnimation.start(file));

        //从assets读取 assets resources

        btnStartAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silkyAnimation.setSupportInBitmap(false);
                silkyAnimation.setRepeatMode(SilkyAnimation.MODE_INFINITE);
                silkyAnimation.start("huabanyu", 40);
            }
        });

//        btnStartAssets.setOnClickListener(view -> {
//                    silkyAnimation.setSupportInBitmap(false);
//                    silkyAnimation.setRepeatMode(SilkyAnimation.MODE_ONCE);
//                    silkyAnimation.start("huabanyu", 40);
//                }
//        );
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                silkyAnimation.stop();
            }
        });

//        btnStop.setOnClickListener(view -> silkyAnimation.stop());
    }

    private void initView() {
        mSurfaceView = (SurfaceView) findViewById(R.id.sv_main);
        btnStartAssets = (Button) findViewById(R.id.btn_start_assets);
        btnStartFile = (Button) findViewById(R.id.btn_start_file);
        btnStop = (Button) findViewById(R.id.btn_stop);
        iv_main = (ImageView) findViewById(R.id.iv_main);
    }

}
