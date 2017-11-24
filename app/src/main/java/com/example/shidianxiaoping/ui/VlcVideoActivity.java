package com.example.shidianxiaoping.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shidianxiaoping.MyApplication;
import com.example.shidianxiaoping.R;
import com.example.shidianxiaoping.beans.BaoCunBean;
import com.example.shidianxiaoping.beans.BaoCunBeanDao;
import com.example.shidianxiaoping.beans.ChuanJianUserBean;
import com.example.shidianxiaoping.beans.MoShengRenBean;
import com.example.shidianxiaoping.beans.MoShengRenBean2;
import com.example.shidianxiaoping.beans.MoShengRenBeanDao;
import com.example.shidianxiaoping.beans.ShiBieBean;
import com.example.shidianxiaoping.beans.TanChuangBean;
import com.example.shidianxiaoping.beans.TuPianBean;
import com.example.shidianxiaoping.beans.User;
import com.example.shidianxiaoping.beans.WBBean;
import com.example.shidianxiaoping.interfaces.RecytviewCash;
import com.example.shidianxiaoping.media.IjkVideoView;
import com.example.shidianxiaoping.media.Settings;
import com.example.shidianxiaoping.service.AlarmReceiver;
import com.example.shidianxiaoping.utils.FileUtils;
import com.example.shidianxiaoping.utils.GlideCircleTransform;
import com.example.shidianxiaoping.utils.GlideRoundTransform;
import com.example.shidianxiaoping.utils.GsonUtil;
import com.example.shidianxiaoping.utils.Utils;
import com.example.shidianxiaoping.view.WrapContentLinearLayoutManager;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class VlcVideoActivity extends BaseActivity implements RecytviewCash {
	private final static String TAG = "WebsocketPushMsg";
	private IjkVideoView ijkVideoView;
	private MyReceiver myReceiver=null;
	private RecyclerView recyclerView;
	private MyAdapter adapter=null;
	private MoShengRenBeanDao daoSession=null;
	//private static List<String> voidePathList=new ArrayList<>();
//	private static List<String> photoPathList=new ArrayList<>();
	private WrapContentLinearLayoutManager manager;
	private static  WebSocketClient webSocketClient=null;
//	private static int shiPingCount=0;
	//private Runnable runnable=null;
	//private Handler conntionHandler=null;
	private String zhuji=null;
	private static final String zhuji2="http://121.46.3.20";
	//private  static Vector<TanChuangBean> tanchuangList=null;
	private  static Vector<TanChuangBean> yuangongList=null;
	private int dw,dh;
	private BaoCunBeanDao baoCunBeanDao=null;
	private BaoCunBean baoCunBean=null;
	private NetWorkStateReceiver netWorkStateReceiver=null;
	private TextView wangluo;
	private boolean isLianJie=false;



	public  Handler handler=new Handler(new Handler.Callback() {

		@Override
		public boolean handleMessage(final Message msg) {
			switch (msg.what){
				case 111:
					//更新地址
//					try {
//
//						WebsocketPushMsg websocketPushMsg=new WebsocketPushMsg();
//						if (zhuji_string!=null && shiping_string!=null){
//
//							websocketPushMsg.startConnection(zhuji_string,shiping_string);
//						}else {
//
//							TastyToast.makeText(VlcVideoActivity.this, "请先设置主机和视频流", Toast.LENGTH_LONG,TastyToast.ERROR).show();
//
//						}
//
//					} catch (URISyntaxException e ) {
//
//						Log.d(TAG, e.getMessage());
//					}

					break;
				case 110:
//					Log.d(TAG, "dddd111");
//					ijkVideoView.setVisibility(View.VISIBLE);
//					//播放广告视频
//					if (voidePathList.size()!=0)
//						play(voidePathList.get(0));
//
////					if (photoPathList.size()!=0){
////						rollPagerView.setAdapter(new TestLoopAdapter(rollPagerView));
////						rollPagerView.setHintView(null);
////
////					}

					//员工弹窗消失
					if (yuangongList.size()>1) {
						yuangongList.remove(1);

//						adapter2.notifyItemRemoved(1);
//						//adapter.notifyItemChanged(1);
//						//adapter.notifyItemRangeChanged(1,tanchuangList.size());
//						//adapter.notifyDataSetChanged();
//						manager2.scrollToPosition(yuangongList.size() - 1);
//						//Log.d(TAG, "tanchuangList.size():" + tanchuangList.size());

					}

					break;
				case 999:

					if (yuangongList.size()>0) {
						yuangongList.remove(0);

					//	adapter.notifyItemRemoved(0);
						//adapter.notifyItemChanged(0);
						adapter.notifyItemRangeChanged(0,yuangongList.size()+1);
						//adapter.notifyDataSetChanged();
						//manager.scrollToPosition(yuangongList.size() - 1);
					}



					break;
				case 19: //更新识别记录
					Log.d(TAG, "19");

					//adapter2.notifyItemInserted(size-1);
					//manager2.smoothScrollToPosition(recyclerView2,null,size-1);

					break;

			}

			if (msg.arg1==1){
				ShiBieBean.PersonBeanSB dataBean= (ShiBieBean.PersonBeanSB) msg.obj;

				try {

					final TanChuangBean bean=new TanChuangBean();
					bean.setBytes(null);
					bean.setType(dataBean.getSubject_type());
					bean.setName(dataBean.getName());
					bean.setRemark(dataBean.getRemark());
					bean.setTouxiang(dataBean.getAvatar());

					switch (dataBean.getSubject_type()){
						case 0: //员工
							//Log.d(TAG, "员工k");
							yuangongList.add(bean);
							int i1=yuangongList.size();
							adapter.notifyItemInserted(i1);
							manager.scrollToPosition(i1-1);
							new Thread(new Runnable() {
								@Override
								public void run() {

									try {
										Thread.sleep(10000);

										Message message=Message.obtain();
										message.what=999;
										handler.sendMessage(message);


									} catch (InterruptedException e) {
										e.printStackTrace();
									}


								}
							}).start();

							break;
						case 1: //普通访客
							yuangongList.add(bean);
							int i2=yuangongList.size();
							adapter.notifyItemInserted(i2);
							manager.scrollToPosition(i2-1);
							new Thread(new Runnable() {
								@Override
								public void run() {

									try {
										Thread.sleep(10000);

										Message message=Message.obtain();
										message.what=999;
										handler.sendMessage(message);

									} catch (InterruptedException e) {
										e.printStackTrace();
									}


								}
							}).start();

							break;
						case 2:  //VIP访客
							yuangongList.add(bean);
							int i3=yuangongList.size();
							adapter.notifyItemInserted(i3);
							manager.scrollToPosition(i3-1);

							new Thread(new Runnable() {
								@Override
								public void run() {

									try {
										Thread.sleep(10000);
										Message message=Message.obtain();
										message.what=999;
										handler.sendMessage(message);

									} catch (InterruptedException e) {
										e.printStackTrace();
									}


								}
							}).start();


							break;

					}

				} catch (Exception e) {
					//Log.d("WebsocketPushMsg", e.getMessage());
					e.printStackTrace();
				}

//			}else if (msg.arg1==2) {
//
//			final WeiShiBieBean dataBean = (WeiShiBieBean) msg.obj;
//
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//
//						try {
//
//							BASE64Decoder decoder = new BASE64Decoder();
//							// Base64解码
//							final byte[][] b;
//
//							b = new byte[][]{decoder.decodeBuffer(dataBean.getFace().getImage())};
//							for (int i = 0; i < b[0].length; ++i) {
//								if (b[0][i] < 0) {// 调整异常数据
//									b[0][i] += 256;
//								}
//							}
//
//							TanChuangBean bean = new TanChuangBean();
//							bean.setBytes(b[0]);
//							bean.setName("陌生人");
//							bean.setType(-1);
//							bean.setTouxiang(null);
//							yuangongList.add(bean);
//							final int i3=yuangongList.size();
//							runOnUiThread(new Runnable() {
//								@Override
//								public void run() {
//
//									adapter.notifyItemInserted(i3);
//									manager.scrollToPosition(i3 - 1);
//								}
//							});
//
//							Thread.sleep(10000);
////
////											ViewAnimator
////													.animate(adapter.getViewByPosition(recyclerView,1,R.layout.tanchuang_item))
////													.scale(1,0)
////													.duration(1000)
////													.start();
////
////								if (tanchuangList.size()>1)
////									tanchuangList.remove(1);
//
//							Message message = Message.obtain();
//							message.what = 110;
//							handler.sendMessage(message);
//
//
//						} catch (Exception e) {
//
//							Log.d(TAG, e.getMessage() + "陌生人解码");
//						}
//
//					}
//				}).start();
			}

			return false;
		}
	});

//	/*
//     * @param arg0
//     */
//	@Override
//	public void onSynthesizeStart(String utteranceId) {
//	//	toPrint("onSynthesizeStart utteranceId=" + utteranceId);
//	}
//
//	/**
//	 * 合成数据和进度的回调接口，分多次回调
//	 *
//	 * @param
//	 * @param data 合成的音频数据。该音频数据是采样率为16K，2字节精度，单声道的pcm数据。
//	 * @param progress 文本按字符划分的进度，比如:你好啊 进度是0-3
//	 */
//	@Override
//	public void onSynthesizeDataArrived(String utteranceId, byte[] data, int progress) {
//		// toPrint("onSynthesizeDataArrived");
//		//mHandler.sendMessage(mHandler.obtainMessage(UI_CHANGE_SYNTHES_TEXT_SELECTION, progress, 0));
//	}
//
//	/**
//	 * 合成正常结束，每句合成正常结束都会回调，如果过程中出错，则回调onError，不再回调此接口
//	 *
//	 * @param
//	 */
//	@Override
//	public void onSynthesizeFinish(String utteranceId) {
//		//toPrint("onSynthesizeFinish utteranceId=" + utteranceId);
//	}
//
//	/**
//	 * 播放开始，每句播放开始都会回调
//	 *
//	 * @param
//	 */
//	@Override
//	public void onSpeechStart(String utteranceId) {
//		//toPrint("onSpeechStart utteranceId=" + utteranceId);
//	}
//
//	/**
//	 * 播放进度回调接口，分多次回调
//	 *
//	 * @param
//	 * @param progress 文本按字符划分的进度，比如:你好啊 进度是0-3
//	 */
//	@Override
//	public void onSpeechProgressChanged(String utteranceId, int progress) {
//		// toPrint("onSpeechProgressChanged");
//		//mHandler.sendMessage(mHandler.obtainMessage(UI_CHANGE_INPUT_TEXT_SELECTION, progress, 0));
//	}
//
//	/**
//	 * 播放正常结束，每句播放正常结束都会回调，如果过程中出错，则回调onError,不再回调此接口
//	 *
//	 * @param utteranceId
//	 */
//	@Override
//	public void onSpeechFinish(String utteranceId) {
//		//toPrint("onSpeechFinish utteranceId=" + utteranceId);
//	}
//
//	/**
//	 * 当合成或者播放过程中出错时回调此接口
//	 *
//	 * @param utteranceId
//	 * @param error 包含错误码和错误信息
//	 */
//	@Override
//	public void onError(String utteranceId, SpeechError error) {
//	//	toPrint("onError error=" + "(" + error.code + ")" + error.description + "--utteranceId=" + utteranceId);
//	}

	@Override
	public void reset() {

		//数据重置
		chongzhi();
	}

	private void chongzhi(){
		yuangongList.clear();
		//tanchuangList.clear();
		if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (!recyclerView.isComputingLayout())) {
			adapter.notifyDataSetChanged();
		}


//
//		TanChuangBean bean=new TanChuangBean();
//		bean.setBytes(null);
//		bean.setName(null);
//		bean.setType(-2);
//		bean.setTouxiang(null);
//		//tanchuangList.add(bean);
//
//		TanChuangBean bean5=new TanChuangBean();
//		bean5.setBytes(null);
//		bean5.setName(null);
//		bean5.setType(-2);
//		bean5.setTouxiang(null);
//		//tanchuangList.add(bean5);
//
//		TanChuangBean bean3=new TanChuangBean();
//		bean3.setBytes(null);
//		bean3.setName(null);
//		bean3.setType(-2);
//		bean3.setTouxiang(null);
//		yuangongList.add(bean3);

	}

//	private class TestLoopAdapter extends LoopPagerAdapter {
//
//		public TestLoopAdapter(RollPagerView viewPager) {
//			super(viewPager);
//		}
//
//		@Override
//		public View getView(ViewGroup container, int position) {
//			ImageView view = new ImageView(container.getContext());
//			try {
//
//				view.setImageDrawable(getImageDrawable(photoPathList.get(position)));
//			} catch (IOException e) {
//				Log.d("test", e.getMessage());
//			}
//
//			view.setScaleType(ImageView.ScaleType.FIT_XY);
//			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//			return view;
//		}
//
//		@Override
//		public int getRealCount() {
//			return photoPathList.size();
//		}
//	}
	/**
	 * 将文件生成位图
	 * @param
	 * @return
	 * @throws
	 */
//	public BitmapDrawable getImageDrawable(String path)
//			throws IOException
//	{
//		//打开文件
//		File file = new File(path);
//		if(!file.exists())
//		{
//			return null;
//		}
//
//		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		byte[] bt = new byte[1024];
//
//		//得到文件的输入流
//		InputStream in = new FileInputStream(file);
//
//		//将文件读出到输出流中
//		int readLength = in.read(bt);
//		while (readLength != -1) {
//			outStream.write(bt, 0, readLength);
//			readLength = in.read(bt);
//		}
//
//		//转换成byte 后 再格式化成位图
//		byte[] data = outStream.toByteArray();
//		Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// 生成位图
//		BitmapDrawable bd = new BitmapDrawable(getResources(),bitmap);
//
//		return bd;
//	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//	Log.d(TAG, "创建111");


		baoCunBeanDao=MyApplication.myApplication.getDaoSession().getBaoCunBeanDao();
		baoCunBean=baoCunBeanDao.load(123456L);
		if (baoCunBean==null ){
			BaoCunBean baoCunBean=new BaoCunBean();
			baoCunBean.setId(123456L);
			baoCunBeanDao.insert(baoCunBean);
		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
		//DisplayMetrics dm = getResources().getDisplayMetrics();
		dw = Utils.getDisplaySize(VlcVideoActivity.this).x;
		dh = Utils.getDisplaySize(VlcVideoActivity.this).y;

		setContentView(R.layout.activity_video_vlc);
		wangluo= (TextView) findViewById(R.id.wangluo);

//		if (baoCunBean!=null && baoCunBean.getIsHengOrShu()){
//          //  Log.d(TAG, "横屏");
//            isHX=true;
//			/**
//			 * 设置为横屏
//			 */
//			if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//			}
//
//		}else {
//			isHX=false;
//			/**
//			 * 设置为竖屏
//			 */
//			if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
//				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                Log.d(TAG, "竖屏");
//			}
//		}

	//	w = dm.widthPixels;
	//	h = dm.heightPixels;


		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					//JPushInterface.setAlias(VlcVideoActivity.this,1,"children");
					//百度语音
					//initialEnv();
					//initialTts();
				}
			}).start();
		}catch (Exception e){
			Log.d(TAG, e.getMessage()+"");
		}

		IjkMediaPlayer.loadLibrariesOnce(null);
		IjkMediaPlayer.native_profileBegin("libijkplayer.so");

	//	tanchuangList=new Vector<>();
		yuangongList=new Vector<>();

//		TanChuangBean bean=new TanChuangBean();
//		bean.setBytes(null);
//		bean.setName(null);
//		bean.setType(-2);
//		bean.setTouxiang(null);
//		tanchuangList.add(bean);
//
//		TanChuangBean bean2=new TanChuangBean();
//		bean2.setBytes(null);
//		bean2.setName(null);
//		bean2.setType(-2);
//		bean2.setTouxiang(null);
//		tanchuangList.add(bean2);
//
//		TanChuangBean bean3=new TanChuangBean();
//		bean3.setBytes(null);
//		bean3.setName(null);
//		bean3.setType(-2);
//		bean3.setTouxiang(null);
//		yuangongList.add(bean3);



		Button button= (Button) findViewById(R.id.ddd);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chongzhi();

				startActivity(new Intent(VlcVideoActivity.this, SheZhiActivity.class));
			}
		});


		//实例化过滤器并设置要过滤的广播
		myReceiver = new MyReceiver();
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("duanxianchonglian");
		intentFilter.addAction("gxshipingdizhi");
		intentFilter.addAction("shoudongshuaxin");
		intentFilter.addAction("updateGonggao");
		intentFilter.addAction("updateTuPian");
		intentFilter.addAction("updateShiPing");
		intentFilter.addAction("delectShiPing");
		intentFilter.addAction("guanbi");
		intentFilter.addAction(Intent.ACTION_TIME_TICK);

		// 注册广播
		registerReceiver(myReceiver, intentFilter);

//		myReceiverFile = new MyReceiverFile();
//		IntentFilter intentFilter2 = new IntentFilter();
//		intentFilter2.addAction(Intent.ACTION_MEDIA_EJECT);
//		intentFilter2.addAction(Intent.ACTION_MEDIA_MOUNTED);
//		intentFilter2.addDataScheme("file");  //与其它 action 有冲突
//		registerReceiver(myReceiverFile, intentFilter2);

		//logo = BitmapFactory.decodeResource(super.getResources(), R.drawable.yixuanze_22);
		daoSession = MyApplication.getAppContext().getDaoSession().getMoShengRenBeanDao();
		daoSession.deleteAll();

		//shiBieJiLuBeanDao = MyApplication.getAppContext().getDaoSession().getShiBieJiLuBeanDao();
		//shiPingBeanDao = MyApplication.getAppContext().getDaoSession().getShiPingBeanDao();

		recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

//		recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
//			//用来标记是否正在向最后一个滑动
//			boolean isSlidingToLast = false;
//
//			@Override
//			public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//				super.onScrollStateChanged(recyclerView, newState);
//				LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//				// 当不滚动时
//				if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//					//获取最后一个完全显示的ItemPosition
//					int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//					int totalItemCount = manager.getItemCount();
//
//					// 判断是否滚动到底部，并且是向右滚动
//					if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
//						//加载更多功能的代码
//						manager2.smoothScrollToPosition(recyclerView2,null,0);
//					}
//
//					if (lastVisibleItem==4 && !isSlidingToLast){
//						manager2.smoothScrollToPosition(recyclerView2,null,shiBieJiLuList.size()-1);
//					}
//
//				}
//			}
//
//			@Override
//			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//				super.onScrolled(recyclerView, dx, dy);
//
//				//dx用来判断横向滑动方向，dy用来判断纵向滑动方向
//				//大于0表示正在向下滚动
//				//小于等于0表示停止或向上滚动
//				isSlidingToLast = dy > 0;
//			}
//		});


		//	mSurfaceView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//		LinearLayout relativeLayout= (LinearLayout) findViewById(R.id.ffgghh);
//		relativeLayout.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//
//
//			}
//		});

//		Log.d(TAG, "findViewById(R.id.toptv).getWidth():" + findViewById(R.id.toptv).getWidth());

		//mLoadingView = findViewById(R.id.video_loading);


		//mSurfaceHolder = mSurfaceView.getHolder();
		//mSurfaceHolder.setFormat(PixelFormat.RGBX_8888);
		//mSurfaceHolder.addCallback(this);
	//	mSurfaceView.setKeepScreenOn(true);


		ijkVideoView = (IjkVideoView) findViewById(R.id.vitamio);

		ijkVideoView.setHudView(new TableLayout(this));
		//this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		//		mMediaPlayer.setMediaList();
		//		mMediaPlayer.getMediaList().add(new Media(mMediaPlayer, "http://live.3gv.ifeng.com/zixun.m3u8"), false);
		//		mMediaPlayer.playIndex(0);
		//	mMediaPlayer.playMRL("rtsp://192.168.2.52/user=admin&password=&channel=1&stream=0.sdp");

		Settings mSettings = new Settings(this);
		IjkMediaPlayer	ijkMediaPlayer=new IjkMediaPlayer();
		ijkMediaPlayer.setLogEnabled(false);

		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 1);
		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", 1);
		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", 1);
		ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 60);

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "max-fps", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "fps", 30);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_YV12);

        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "packet-buffering", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "fflags", "nobuffer");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "max-buffer-size", 1024);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "min-frames", 10);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probsize", "4096");
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", "2000000");

		//String rtspUrl="http://112.253.22.157/17/z/z/y/u/zzyuasjwufnqerzvyxgkuigrkcatxr/hc.yinyuetai.com/D046015255134077DDB3ACA0D7E68D45.flv";
		//String rtspUrl="mnt/usb_storage/USB_DISK1"+File.separator+"a1.mp4";
		//ijkVideoView.setVideoURI(Uri.parse(rtspUrl));
		//ijkVideoView.setVideoPath(rtspUrl);
//		File storage = new File("/storage");
//		File[] files = storage.listFiles();
//		for (final File file : files) {
//			if (file.canRead()) {
//				if (!file.getName().equals(Environment.getExternalStorageDirectory().getName())) {
//					//满足该条件的文件夹就是u盘在手机上的目录
//					Log.d(TAG, file.getName());
//					Log.d(TAG, Environment.getExternalStorageDirectory().getName());
//
//				}
//			}
//		}
//		File file=new File("mnt/usb_storage/USB_DISK1"+File.separator+"a1.mp4");
//		Log.d(TAG, "file.length():" + file.length());

		 manager = new WrapContentLinearLayoutManager(this);
		manager.setOrientation(LinearLayoutManager.HORIZONTAL);
		recyclerView.setLayoutManager(manager);
	//	recyclerView.addItemDecoration(new RecycleViewDivider(VlcVideoActivity.this, LinearLayoutManager.VERTICAL));
		//recyclerView.addItemDecoration(new MyDecoration(VlcVideoActivity.this, LinearLayoutManager.VERTICAL,20,R.color.transparent));

		adapter = new MyAdapter(R.layout.tanchuang_item,yuangongList);
		recyclerView.setAdapter(adapter);



//		shiPingBeanList=shiPingBeanDao.loadAll();
//		Log.d("VlcVideoActivity", "获取视频个数:" + shiPingBeanList.size());
//		try {
//
//			if (shiPingBeanList.size()>0){
//				for (int i=0;i<shiPingBeanList.size();i++){
//					if (shiPingBeanList.get(i).getIsDownload()){
//
//						ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory()+File.separator+"linhefile"+File.separator+shiPingBeanList.get(0).getId()+"."+shiPingBeanList.get(0).getVideoType().split("\\/")[1]);
//
//					}else {
//						Log.d(TAG, "删除没下载的视频记录");
//						shiPingBeanDao.deleteByKey(shiPingBeanList.get(i).getId());
//					}
//				}
//			}
//


//		}catch (Exception e){
//			Log.d(TAG, "得到本地视频个数异常"+e.getMessage());
//		}

		FileUtils.getInstance(this).copyAssetsToSD("mp4","mymp4").setFileOperateCallback(new FileUtils.FileOperateCallback() {
			@Override
			public void onSuccess() {

				ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory()+File.separator+"mymp4"+File.separator+"ccc.mp4");
				ijkVideoView.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
					@Override
					public void onCompletion(IMediaPlayer iMediaPlayer) {

						ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory()+File.separator+"mymp4"+File.separator+"ccc.mp4");
						ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
							@Override
							public void onPrepared(IMediaPlayer iMediaPlayer) {
								ijkVideoView.start();
							}
						});

					}
				});
				ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
					@Override
					public void onPrepared(IMediaPlayer iMediaPlayer) {
						ijkVideoView.start();
					}
				});
				ijkVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
					@Override
					public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
						//Log.d(TAG, "i:" + i);
						//Log.d(TAG, "i1:" + i1);
						Toast toast=TastyToast.makeText(VlcVideoActivity.this,"播放视频出错",TastyToast.LENGTH_LONG,TastyToast.ERROR);
						toast.setGravity(Gravity.CENTER,0,0);
						toast.show();



						return true;
					}
				});
			}

			@Override
			public void onFailed(String error) {

			}
		});





//		Type resultType5 = new TypeToken<GongGaoBean>() {}.getType();
//		Reservoir.getAsync("baocungonggao", resultType5, new ReservoirGetCallback<GongGaoBean>() {
//			@Override
//			public void onSuccess(GongGaoBean strings) {
//				ss=strings.getContent();
//
//				if (gonggaoList.size()>0){
//					gonggaoList.clear();
//				}
//				if (ss.length()>94){
//
//					StringBuilder sb=new StringBuilder();
//					//遍历原始字符串的每一位字符,把它依次加入到sb中
//					for(int i=0;i<ss.length();i++){
//						sb.append((ss.charAt(i)));//依次加入sb中
//						if(i%94==0){
//							if (i>0){
//								sb.append("&");
//							}
//						}
//					}
//					String[] sourceStrArray = sb.toString().split("&");
//					for (String aSourceStrArray : sourceStrArray) {
//						gonggaoList.add("     "+aSourceStrArray);
//						//Log.d(TAG, aSourceStrArray);
//					}
//
//					handlerGongGao.postDelayed(runnableGongGao, 1);//启动handler，实现4秒定时循环执行
//
//				}else {
//					gonggao.startAnimation(sato0);
//					gonggao.setText(ss);
//
//				}
//
//				gonggaobiaoti.setText(strings.getTitle());
//				gonggaoriqi.setText(strings.getTimeDays());
//
//			}
//
//			@Override
//			public void onFailure(Exception e) {
//
//			}
//
//		});
//		sato0.setDuration(800);
//		sato1.setDuration(800);

//		sato0.setAnimationListener(new Animation.AnimationListener() {
//			@Override
//			public void onAnimationStart(Animation animation) {
//
//			}
//
//			@Override
//			public void onAnimationEnd(Animation animation) {
//				gonggao.startAnimation(sato1);
//
//			}
//
//			@Override
//			public void onAnimationRepeat(Animation animation) {
//
//			}
//		});

		//link_chengshi();

	}

	private void initialEnv() {
//		if (mSampleDirPath == null) {
//			String sdcardPath = Environment.getExternalStorageDirectory().toString();
//			mSampleDirPath = sdcardPath + "/" + SAMPLE_DIR_NAME;
//		}
//		Utils.makeDir(mSampleDirPath);
//		Utils.copyFromAssetsToSdcard(false, SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_FEMALE_MODEL_NAME,this);
//		Utils.copyFromAssetsToSdcard(false, SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/" + SPEECH_MALE_MODEL_NAME,this);
//		Utils.copyFromAssetsToSdcard(false, TEXT_MODEL_NAME, mSampleDirPath + "/" + TEXT_MODEL_NAME,this);
//		//Utils.copyFromAssetsToSdcard(false, LICENSE_FILE_NAME, mSampleDirPath + "/" + LICENSE_FILE_NAME,this);
//		Utils.copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME, mSampleDirPath + "/"
//				+ ENGLISH_SPEECH_FEMALE_MODEL_NAME,this);
//		Utils.copyFromAssetsToSdcard(false, "english/" + ENGLISH_SPEECH_MALE_MODEL_NAME, mSampleDirPath + "/"
//				+ ENGLISH_SPEECH_MALE_MODEL_NAME,this);
//		Utils.copyFromAssetsToSdcard(false, "english/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath + "/"
//				+ ENGLISH_TEXT_MODEL_NAME,this);
	}

	private void initialTts() {
//		this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
//		this.mSpeechSynthesizer.setContext(this);
//		this.mSpeechSynthesizer.setSpeechSynthesizerListener(this);
//		// 文本模型文件路径 (离线引擎使用)
//		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_TEXT_MODEL_FILE, mSampleDirPath + "/"
//				+ TEXT_MODEL_NAME);
//		// 声学模型文件路径 (离线引擎使用)
//		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_SPEECH_MODEL_FILE, mSampleDirPath + "/"
//				+ SPEECH_FEMALE_MODEL_NAME);
//		// 本地授权文件路径,如未设置将使用默认路径.设置临时授权文件路径，LICENCE_FILE_NAME请替换成临时授权文件的实际路径，仅在使用临时license文件时需要进行设置，如果在[应用管理]中开通了正式离线授权，不需要设置该参数，建议将该行代码删除（离线引擎）
//		// 如果合成结果出现临时授权文件将要到期的提示，说明使用了临时授权文件，请删除临时授权即可。
//		//this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_TTS_LICENCE_FILE, mSampleDirPath + "/"
//			//	+ LICENSE_FILE_NAME);
//		// 请替换为语音开发者平台上注册应用得到的App ID (离线授权)
//		this.mSpeechSynthesizer.setAppId("9990556"/*这里只是为了让Demo运行使用的APPID,请替换成自己的id。*/);
//		// 请替换为语音开发者平台注册应用得到的apikey和secretkey (在线授权)
//		this.mSpeechSynthesizer.setApiKey("0fxLiYpG9gUC9660agtU3rEU",
//				"68288f5f80eaa85a6384ac30a14b622e"/*这里只是为了让Demo正常运行使用APIKey,请替换成自己的APIKey*/);
//		// 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
//		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "4");
//		// 设置Mix模式的合成策略
//		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
//		//合成引擎速度优化等级，取值范围[0, 2]，值越大速度越快（离线引擎）
//		this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOCODER_OPTIM_LEVEL, "2");
//
//		// 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
//		// AuthInfo接口用于测试开发者是否成功申请了在线或者离线授权，如果测试授权成功了，可以删除AuthInfo部分的代码（该接口首次验证时比较耗时），不会影响正常使用（合成使用时SDK内部会自动验证授权）
//		AuthInfo authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);
//
//		if (authInfo.isSuccess()) {
//			toPrint("auth success");
//		} else {
//			String errorMsg = authInfo.getTtsError().getDetailMessage();
//			toPrint("auth failed errorMsg=" + errorMsg);
//		}
//
//		// 初始化tts
//		if (mSpeechSynthesizer!=null){
//			mSpeechSynthesizer.initTts(TtsMode.MIX);
//			// 加载离线英文资源（提供离线英文合成功能）
//			int result =
//					mSpeechSynthesizer.loadEnglishModel(mSampleDirPath + "/" + ENGLISH_TEXT_MODEL_NAME, mSampleDirPath
//							+ "/" + ENGLISH_SPEECH_FEMALE_MODEL_NAME);
//			toPrint("loadEnglishModel result=" + result);
//		}


	}

//	private void toPrint(String str) {
//		Message msg = Message.obtain();
//		msg.obj = str;
//		this.mHandler.sendMessage(msg);
//	}

//	private Handler mHandler = new Handler() {
//		/*
//         * @param msg
//         */
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			int what = msg.what;
//			switch (what) {
//				case PRINT:
//					print(msg);
//					break;
////				case UI_CHANGE_INPUT_TEXT_SELECTION:  //播放进度
////					if (msg.arg1 <= mInput.getText().length()) {
////						mInput.setSelection(0,msg.arg1);
////					}
////					break;
////				case UI_CHANGE_SYNTHES_TEXT_SELECTION:  //合成进度
////					SpannableString colorfulText = new SpannableString(mInput.getText().toString());
////					if (msg.arg1 <= colorfulText.toString().length()) {
////						colorfulText.setSpan(new ForegroundColorSpan(Color.GRAY), 0, msg.arg1, Spannable
////								.SPAN_EXCLUSIVE_EXCLUSIVE);
////						mInput.setText(colorfulText);
////					}
////					break;
//				default:
//					break;
//			}
//		}
//
//	};

//	private void print(Message msg) {
//		String message = (String) msg.obj;
//		if (message != null) {
//			Log.w(TAG, message);
//			//Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//
//		}
//	}

//
//	/**
//	 * 打印引擎so库版本号及基本信息和model文件的基本信息
//	 */
//	private void printEngineInfo() {
//		toPrint("EngineVersioin=" + SynthesizerTool.getEngineVersion());
//		toPrint("EngineInfo=" + SynthesizerTool.getEngineInfo());
//		String textModelInfo = SynthesizerTool.getModelInfo(mSampleDirPath + "/" + TEXT_MODEL_NAME);
//		toPrint("textModelInfo=" + textModelInfo);
//		String speechModelInfo = SynthesizerTool.getModelInfo(mSampleDirPath + "/" + SPEECH_FEMALE_MODEL_NAME);
//		toPrint("speechModelInfo=" + speechModelInfo);
//	}

//	// 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
//	private void getAllFiles(File root){
//		File files[] = root.listFiles();
//
//
//		if(files != null){
//			for (File f : files){
//				if(f.isDirectory()){
//					getAllFiles(f);
//				}else{
//				String name=f.getName();
//					if (name.substring(name.length()-3,name.length()).equals("mp4")){
//						Log.d(TAG, f.getAbsolutePath());
//						voidePathList.add(f.getAbsolutePath());
//
//					}else if (name.substring(name.length()-3,name.length()).equals("jpg")||name.substring(name.length()-3,name.length()).equals("png")){
//						photoPathList.add(f.getAbsolutePath());
//					}
//
//				}
//			}
//			Log.d(TAG, "voidePathList.size():" + voidePathList.size());
//			Message message=Message.obtain();
//			message.what=110;
//			handler.sendMessage(message);
//
//		}
//	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	public  class MyAdapter extends BaseQuickAdapter<TanChuangBean,BaseViewHolder> {

		private MyAdapter(int layoutResId, List<TanChuangBean> data) {
			super(layoutResId, data);

		}

//		private View getView(int p){
//			this.p=p;
//			return view;
//		}

		@Override
		protected void convert(BaseViewHolder helper, TanChuangBean item) {
			//Log.d(TAG, "动画执行");
//			ViewAnimator
//					.animate(helper.itemView)
//					.scale(0,1)
////					.alpha(0,1)
//					.duration(1000)
//					.start();

		//	LinearLayout toprl= helper.getView(R.id.top11111);

			ImageView imageView= helper.getView(R.id.touxiang);
			TextView name=helper.getView(R.id.name33);
			TextView zhuangtai=helper.getView(R.id.zhuangtai33);
			//TextView name=helper.getView(R.id.name);
			//TextView autoScrollTextView=helper.getView(R.id.richeng);
			//Log.d("VlcVi", "item.getType():" + item.getType());


				switch (item.getType()) {
					case -1:
						//陌生人
						//	toprl.setBackgroundResource(R.drawable.zidonghuoqu15);

						//	richeng.setText("欢迎来访本幼儿园");
						//	name.setText("");
						//time.setText(DateUtils.times(System.currentTimeMillis()));
						//autoScrollTextView.setText("");


						break;
					case 0:
						//员工

					 name.setText(item.getName());
						String  zt=item.getRemark();
						if (zt!=null){
							if (zt.equals("")){
								zhuangtai.setText("员工");
							}else {
								zhuangtai.setText(zt);
							}
						}else {
							zhuangtai.setText("员工");
						}

						break;

					case 1:
						//访客
						//toprl.setBackgroundResource(R.drawable.zidonghuoqu15);

						//richeng.setText("");
						//name.setText(item.getName());
						//autoScrollTextView.setText("欢迎你来本公司参观指导。");

						break;
					case 2:
						//VIP访客
						//	toprl.setBackgroundResource(R.drawable.ms_bg);
						//	richeng.setText("");
						//	name.setText(item.getName());
						//autoScrollTextView.setText("欢迎VIP访客 "+item.getName()+" 来本公司指导工作。");

						break;
				}




			if (item.getTouxiang()!=null){

				Glide.with(MyApplication.getAppContext())
					//	.load(zhuji+item.getTouxiang())
						.load("http://121.46.3.20/"+item.getTouxiang())
						.transform(new GlideCircleTransform(MyApplication.getAppContext(),2,Color.parseColor("#ffffffff")))
						//.transform(new GlideRoundTransform(MyApplication.getAppContext(), 6))
						.into((ImageView) helper.getView(R.id.touxiang));
			}else {
				Glide.with(MyApplication.getAppContext())
						.load(item.getBytes())
						//.transform(new GlideCircleTransform(MyApplication.getAppContext(),2,Color.parseColor("#ffffffff")))
						.transform(new GlideRoundTransform(MyApplication.getAppContext(), 6))
						.into((ImageView) helper.getView(R.id.touxiang));

			}


			RelativeLayout linearLayout_tanchuang = helper.getView(R.id.ffflll);
				ViewGroup.LayoutParams lp =  linearLayout_tanchuang.getLayoutParams();

			    //弹窗的高宽

				lp.width=dw/2-100;
				lp.height=(dh*2)/3;
				linearLayout_tanchuang.setLayoutParams(lp);
			    linearLayout_tanchuang.invalidate();

		}

	}



	private  class MyAdapter2 extends BaseQuickAdapter<TanChuangBean,BaseViewHolder> {

		private MyAdapter2(int layoutResId, List<TanChuangBean> data) {
			super(layoutResId, data);

		}


		@Override
		protected void convert(BaseViewHolder helper, TanChuangBean item) {
			Log.d(TAG, "2动画执行");
			ViewAnimator
					.animate(helper.itemView)
					.scale(0,1)
//					.alpha(0,1)
					.duration(1000)
					.start();
			ImageView imageView= helper.getView(R.id.touxiang2);
			ImageView logo= helper.getView(R.id.logo_bg);
			TextView type= helper.getView(R.id.type);
			TextView name2= helper.getView(R.id.name2);
			RelativeLayout linearLayout_tanchuang = helper.getView(R.id.ddyy);

			if (helper.getLayoutPosition()==0){
				linearLayout_tanchuang.setBackground(null);
				imageView.setImageBitmap(null);
				logo.setImageBitmap(null);
				type.setText("");
				name2.setText("");

			}else {
				switch (item.getType()){
					case -1:
						//陌生人
						linearLayout_tanchuang.setBackgroundResource(R.drawable.ms_bg);
						logo.setImageResource(R.drawable.ruitong_logo);
						type.setText("陌生人");
						name2.setText("");

						break;
					case 0:
						//员工
						linearLayout_tanchuang.setBackgroundResource(R.drawable.yg_bg);
						logo.setImageResource(R.drawable.ruitong_logo);
						type.setText("员工");
						name2.setText(item.getName());

						break;

					case 1:
						//访客
						linearLayout_tanchuang.setBackgroundResource(R.drawable.yg_bg);
						logo.setImageResource(R.drawable.ruitong_logo);
						type.setText("访客");
						name2.setText(item.getName());
						break;

				}


				if (item.getTouxiang()!=null){

					Glide.with(MyApplication.getAppContext())
							.load(zhuji+item.getTouxiang())
							.transform(new GlideCircleTransform(MyApplication.getAppContext(),2,Color.parseColor("#ffffffff")))
							//.transform(new GlideRoundTransform(MyApplication.getAppContext(), 6))
							.into(imageView);
				}else {
					Glide.with(MyApplication.getAppContext())
							.load(item.getBytes())
							.transform(new GlideCircleTransform(MyApplication.getAppContext(),2,Color.parseColor("#ffffffff")))
							//.transform(new GlideRoundTransform(MyApplication.getAppContext(), 6))
							.into(imageView);
				}
			}


			ViewGroup.LayoutParams lp =  linearLayout_tanchuang.getLayoutParams();
			RelativeLayout.LayoutParams touxiang_im= (RelativeLayout.LayoutParams) imageView.getLayoutParams();
			//员工头像大小
			touxiang_im.width=dh/5+50;
			touxiang_im.height=dh/5+50;
			touxiang_im.topMargin=(dh)/5-40;
			imageView.setLayoutParams(touxiang_im);
			imageView.invalidate();
			//弹窗宽度
			lp.width=dw/3-30;
			//lp.height=dh/3;
			linearLayout_tanchuang.setLayoutParams(lp);
			linearLayout_tanchuang.invalidate();

			}

	}

//	/**
//	 * 生成二维码
//	 * @param string 二维码中包含的文本信息
//	 * @param mBitmap logo图片
//	 * @param format  编码格式
//	 * [url=home.php?mod=space&uid=309376]@return[/url] Bitmap 位图
//	 * @throws WriterException
//	 */
//	private static final int IMAGE_HALFWIDTH = 1;//宽度值，影响中间图片大小
//	public Bitmap createCode(String string,Bitmap mBitmap, BarcodeFormat format)
//			throws WriterException {
//		Matrix m = new Matrix();
//		float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
//		float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
//		m.setScale(sx, sy);//设置缩放信息
//		//将logo图片按martix设置的信息缩放
//		mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
//				mBitmap.getWidth(), mBitmap.getHeight(), m, false);
//		MultiFormatWriter writer = new MultiFormatWriter();
//		Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();
//		hst.put(EncodeHintType.CHARACTER_SET, "UTF-8");//设置字符编码
//		BitMatrix matrix = writer.encode(string, format, 600, 600, hst);//生成二维码矩阵信息
//		int width = matrix.getWidth();//矩阵高度
//		int height = matrix.getHeight();//矩阵宽度
//		int halfW = width/2;
//		int halfH = height/2;
//		int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息
//		for (int y = 0; y < height; y++) {//从行开始迭代矩阵
//			for (int x = 0; x < width; x++) {//迭代列
//				if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
//						&& y > halfH - IMAGE_HALFWIDTH
//						&& y < halfH + IMAGE_HALFWIDTH) {//该位置用于存放图片信息
//			//记录图片每个像素信息
//					pixels[y * width + x] = mBitmap.getPixel(x - halfW
//							+ IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);              } else {
//					if (matrix.get(x, y)) {//如果有黑块点，记录信息
//						pixels[y * width + x] = 0xff000000;//记录黑块信息
//					}
//				}
//			}
//		}
//		Bitmap bitmap = Bitmap.createBitmap(width, height,
//				Bitmap.Config.ARGB_8888);
//		// 通过像素数组生成bitmap
//		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//		return bitmap;
//	}
//	private class MyReceiverFile  extends BroadcastReceiver {
//
//		@Override
//		public void onReceive(Context context, final Intent intent) {
//			String action = intent.getAction();
//			if (action.equals(Intent.ACTION_MEDIA_EJECT)) {
//				//USB设备移除，更新UI
//				Log.d(TAG, "设备被移出");
////				if (rollPagerView!=null){
////					if (rollPagerView.isPlaying()){
////						rollPagerView.pause();
////					}
////
////
////				}
//				if (ijkMediaPlayer!=null){
//					Log.d(TAG, "播放暂停");
//					ijkVideoView.pause();
//					ijkVideoView.canPause();
//
//					//ijkVideoView.stopPlayback();
//					//ijkMediaPlayer.stop();
//				}
//			} else if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
//				//USB设备挂载，更新UI
//				Log.d(TAG, "设备插入");
//				new Thread(new Runnable() {
//					@Override
//					public void run() {
//						String usbPath = intent.getDataString();//（usb在手机上的路径）
//
//						getAllFiles(new File(usbPath.split("file:///")[1]+File.separator+"file"));
//						Log.d(TAG, usbPath);
//					}
//				}).start();
//			}
//
//		}
//	}


	private class MyReceiver  extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, final Intent intent) {
			//Log.d(TAG, "intent:" + intent.getAction());

		//	if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
//				String time=(System.currentTimeMillis())+"";
//				xiaoshi.setText(DateUtils.timeMinute(time));
//				riqi.setText(DateUtils.timesTwo(time));
//				xingqi.setText(DateUtils.getWeek(System.currentTimeMillis()));
//				if (DateUtils.timeMinute(time).equals("06:00")){
//					link_chengshi();
//				}


		//	} else {
				if (intent.getAction().equals("duanxianchonglian")) {

					//断线重连
					if (webSocketClient!=null){

					//	Log.d(TAG, "进来1");

						if (!isLianJie){
						//	Log.d(TAG, "进来2");
					try {
						baoCunBean=baoCunBeanDao.load(123456L);
						WebsocketPushMsg websocketPushMsg = new WebsocketPushMsg();
						websocketPushMsg.close();
						if (baoCunBean.getZhujiDiZhi() != null && baoCunBean.getShipingIP() != null) {
							websocketPushMsg.startConnection(baoCunBean.getZhujiDiZhi(), "rtsp://"+baoCunBean.getShipingIP()+":554/user=admin_password=tlJwpbo6_channel=1_stream=0.sdp?real_stream");
						}


					} catch (Exception e) {
						Log.d(TAG, e.getMessage()+"aaa");

					}
						}
				}
				if (intent.getAction().equals("gxshipingdizhi")) {
					//更新视频流地址
					//Log.d(TAG, "收到更新地址广播");
					String a = intent.getStringExtra("gxsp");
					String b = intent.getStringExtra("gxzj");
//					if (!a.equals("")) {
//						shiping_string = a;
//						media = new Media(libvlc, Uri.parse(shiping_string));
//						mediaPlayer.setMedia(media);
//						mediaPlayer.play();
//						//mMediaPlayer.playMRL(shiping_string);
//						startActivity(new Intent(VlcVideoActivity.this, ErWeiMaActivity.class));
//					}
//					if (!b.equals("")) {
//
//						zhuji_string = b;
//						try {
//							String[] a1=zhuji_string.split("//");
//							String[] b1=a1[1].split(":");
//							zhuji="http://"+b1[0];
//
//							WebsocketPushMsg websocketPushMsg = new WebsocketPushMsg();
//							if (zhuji_string != null && shiping_string != null) {
//								websocketPushMsg.startConnection(zhuji_string, shiping_string);
//							}
//						} catch (URISyntaxException e) {
//							e.printStackTrace();
//
//						}
//
//					}

				}
				if (intent.getAction().equals("shoudongshuaxin")) {
					baoCunBean=baoCunBeanDao.load(123456L);
					if (baoCunBean.getShipingIP() != null && baoCunBean.getZhujiDiZhi() != null) {

						try {
							WebsocketPushMsg websocketPushMsg = new WebsocketPushMsg();
							websocketPushMsg.close();
							if (baoCunBean.getShipingIP() != null && baoCunBean.getZhujiDiZhi() != null) {
								websocketPushMsg.startConnection(baoCunBean.getZhujiDiZhi(), "rtsp://"+baoCunBean.getShipingIP()+":554/user=admin_password=tlJwpbo6_channel=1_stream=0.sdp?real_stream");
							}
						} catch (Exception e) {
							Log.d(TAG, e.getMessage()+"fghj");
						}



					}

				}
				if (intent.getAction().equals("updateGonggao")){

//					handlerGongGao.removeCallbacks(runnableGongGao);
//
//
//							ss=strings.getContent();
//
//							if (gonggaoList.size()>0){
//								gonggaoList.clear();
//							}
//							if (ss.length()>92){
//								//isChange=true;
//								StringBuilder sb=new StringBuilder();
//								//遍历原始字符串的每一位字符,把它依次加入到sb中
//								for(int i=0;i<ss.length();i++){
//									sb.append((ss.charAt(i)));//依次加入sb中
//									if(i%92==0){
//										if (i>0){
//											sb.append("&");
//										}
//									}
//								}
//								String[] sourceStrArray = sb.toString().split("&");
//								for (String aSourceStrArray : sourceStrArray) {
//									gonggaoList.add("     "+aSourceStrArray);
//								}
//
//								handlerGongGao.postDelayed(runnableGongGao,100);//4秒后再次执行
//
//							}else {
//								gonggao.startAnimation(sato0);
//								gonggao.setText(ss);
//
//							}
//
//							gonggaobiaoti.setText(strings.getTitle());
//							gonggaoriqi.setText(strings.getTimeDays());
//						}

				}
				if (intent.getAction().equals("updateShiPing")){


//					ShiPingBean shiBieJiLuBean=shiPingBeanDao.load(intent.getStringExtra("idid"));
//					Log.d("VlcVideoActivity", "更新视频ID："+shiBieJiLuBean.getId());
//					if (!shiBieJiLuBean.getIsDownload()){
//
//						Intent intent33 = new Intent(VlcVideoActivity.this, DownloadService.class);
//						intent33.putExtra("url", "http://192.168.2.90:8080/rtps/a"+shiBieJiLuBean.getVideo());
//						//intent33.putExtra("url", "http://183.60.197.32/4/w/k/d/l/wkdlmnucuyduwzdqfjtmdvsxfsotsv/he.yinyuetai.com/C033015644E6D35D99022E014A4761A1.flv?sc\\u003d35b0e75f99e3878e\\u0026br\\u003d3138\\u0026vid\\u003d2650626\\u0026aid\\u003d167\\u0026area\\u003dHT\\u0026vst\\u003d2");
//						fileNames=shiBieJiLuBean.getId();
//						try {
//							fileType=shiBieJiLuBean.getVideoType().split("\\/")[1];
//						}catch (Exception e){
//							Log.d(TAG, "截取最后视频格式错误"+e.getMessage());
//							return;
//						}
//
//						intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
//						intent33.putExtra("urlName",fileNames);
//						intent33.putExtra("nameType",fileType);
//						startService(intent33);
//						Log.d(TAG, "收到下载视频的广播　,下载地址"+fileType);
//					}else {
//
//						if (shiPingBeanList.size()>0){
//							shiPingBeanList.clear();
//						}
//						shiPingBeanList=shiPingBeanDao.loadAll();
//
//						ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory()+File.separator+"linhefile"+File.separator+shiBieJiLuBean.getId()+"."+shiBieJiLuBean.getVideoType().split("\\/")[1]);
//						ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//							@Override
//							public void onPrepared(IMediaPlayer iMediaPlayer) {
//								shiPingCount=-1;
//								ijkVideoView.start();
//							}
//						});
//					}

				}
				if (intent.getAction().equals("delectShiPing")){

//					final String idid=intent.getStringExtra("idid");
//
//					Log.d(TAG, "收到删除视频的广播");
//
//					try {
//
////				int ii= shiPingBeanList.size();
////				Log.d(TAG, "iiiiii:" + ii);
////				int i2=0;
////				if (ii>1){
////					for (int i=ii-1;i>=0;i--){
////						Log.d(TAG, "i:" + i);
////
////						if (shiPingBeanList.get(i).getIsDownload()){
////							i2++;
////							if (i2>=1){
////								//视频文件大于２　并且都是下载过的,可以暂停　删除本地文件
////								//删除数据库
//						final String ty=shiPingBeanDao.load(idid).getVideoType().split("\\/")[1];
//
//						new Thread(new Runnable() {
//							@Override
//							public void run() {
//								String path= Environment.getExternalStorageDirectory()+File.separator+"linhefile";
//
//								getAllFiles(new File(path),idid+"."+ty);
//							}
//						}).start();
//
//						shiPingBeanDao.deleteByKey(idid);
//
//						if (ijkVideoView.isPlaying()){
//							ijkVideoView.pause();
//						}
//						//更新视频列表
//						if (shiPingBeanList.size()>0){
//							shiPingBeanList.clear();
//						}
//						shiPingBeanList=shiPingBeanDao.loadAll();
//						int spl=shiPingBeanList.size();
//						Log.d(TAG, "现在shiPingCount:" + shiPingCount);
//						if (spl>0){
//							if (shiPingCount>0){
//								--shiPingCount;
//							}
//							ShiPingBean sssss=shiPingBeanList.get(shiPingCount);
//							ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory()+File.separator+"linhefile"+File.separator+ sssss.getId()+"."+sssss.getVideoType().split("\\/")[1]);
//							ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//								@Override
//								public void onPrepared(IMediaPlayer iMediaPlayer) {
//									ijkVideoView.start();
//								}
//							});
//						}
//
////							}else {
////								TastyToast.makeText(VlcVideoActivity.this,"不能删除，必须有一个可以播放的视频",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
////							}
////						}
////
////					}
////
////				}else {
////					TastyToast.makeText(VlcVideoActivity.this,"不能删除，必须有一个可以播放的视频",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
////				}
//
//					}catch (Exception e){
//						Log.d(TAG, "捕捉到异常delectShiPing"+e.getMessage());
//					}
//
////				}catch (Exception e){
////					Log.d(TAG, e.getMessage());
////					TastyToast.makeText(VlcVideoActivity.this,"删除视频出错",TastyToast.LENGTH_LONG,TastyToast.INFO).show();
////				}


				}
				if (intent.getAction().equals("updateTuPian")){
//					Log.d(TAG, "收到图片推送");
//					Type resultType = new TypeToken<ShiPingBean>() {}.getType();
//					Reservoir.getAsync("baocuntupian", resultType, new ReservoirGetCallback<ShiPingBean>() {
//						@Override
//						public void onSuccess(ShiPingBean strings) {
//							Log.d(TAG, "收到图片推送2");
//							tupianPath=strings.getVideo();
//							Intent intent33 = new Intent(VlcVideoActivity.this, DownloadTuPianService.class);
//							intent33.putExtra("receiver", new DownloadReceiverTuPian(new Handler()));
//							intent33.putExtra("url", "http://192.168.2.90:8080/rtps/a"+tupianPath);
//							startService(intent33);
//							Log.d(TAG, "收到图片推送3");
//						}
//
//						@Override
//						public void onFailure(Exception e) {
//
//
//						}
//
//					});
				}
				if (intent.getAction().equals("guanbi")){
					Log.d(TAG, "关闭");
					finish();
				}


			}
	//	}

	}
	}




	// 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
	public static void getAllFiles(File root,String nameaaa){
		File files[] = root.listFiles();

		if(files != null){
			for (File f : files){
				if(f.isDirectory()){
					getAllFiles(f,nameaaa);
				}else{
					String name=f.getName();
					if (name.equals(nameaaa)){
						Log.d(TAG, "视频文件删除:" + f.delete());
					}
				}
			}
		}
	}

//	private void link_gengxing_erweima() {
//
//		OkHttpClient okHttpClient= MyApplication.getOkHttpClient();
//		RequestBody body = new FormBody.Builder()
//				.add("cmd","getUnSignList")
////                .add("subjectId",zhaoPianBean.getId()+"")
////                .add("subjectPhoto",zhaoPianBean.getAvatar())
//				.build();
//		Request.Builder requestBuilder = new Request.Builder()
//				.header("Content-Type", "application/json")
//				.post(body)
//				.url("http://192.168.2.17:8080/sign");
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//
//				Log.d("AllConnects", "请求获取二维码失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				//Log.d("AllConnects", "请求获取二维码成功"+call.request().toString());
//				//获得返回体
//				//List<YongHuBean> yongHuBeanList=new ArrayList<>();
//				//List<MoShengRenBean2> intlist=new ArrayList<>();
//			//	intlist.addAll(moShengRenBean2List);
//				try {
//
//				if (moShengRenBean2List.size()!=0)
//				 moShengRenBean2List.clear();
//				ResponseBody body = response.body();
//				//  Log.d("AllConnects", "aa   "+response.body().string());
//
//					JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
//					Gson gson=new Gson();
//						int code=jsonObject.get("resultCode").getAsInt();
//						if (code==0){
//					JsonArray array=jsonObject.get("data").getAsJsonArray();
//					int a=array.size();
//					for (int i=0;i<a;i++){
//						YongHuBean zhaoPianBean=gson.fromJson(array.get(i),YongHuBean.class);
//						moShengRenBean2List.add(zhaoPianBean);
//						//Log.d("VlcVideoActivity", zhaoPianBean.getSubjectId());
//					}
//						//	Log.d("VlcVideoActivity", "moShengRenBean2List.size():" + moShengRenBean2List.size());
////					int a1=intlist.size();
////					int b=yongHuBeanList.size();
////
////						for (int i=0;i<a1;i++){
////							for (int j=0;j<b;j++){
////								Log.d("VlcVideoActivity", intlist.get(i).getId()+"ttt");
////								Log.d("VlcVideoActivity", yongHuBeanList.get(j).getSubjectId()+"ttt");
////								if (intlist.get(i).getId().equals(yongHuBeanList.get(j).getSubjectId())){
////									moShengRenBean2List.add(intlist.get(i));
////								}
////							}
////						}
//
//					Message message=Message.obtain();
//					message.what=12;
//					//  message.obj=yongHuBeanList;
//					handler.sendMessage(message);
//					}
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//
//			}
//		});
//
//	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if( KeyEvent.KEYCODE_MENU == keyCode ){  //如果按下的是菜单
			Log.d(TAG, "按下菜单键 ");
			chongzhi();
			//isTiaoZhuang=false;
			startActivity(new Intent(VlcVideoActivity.this, SheZhiActivity.class));

		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {


		if (netWorkStateReceiver == null) {
			netWorkStateReceiver = new NetWorkStateReceiver();
		}
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(netWorkStateReceiver, filter);


		baoCunBean=baoCunBeanDao.load(123456L);

		if (baoCunBean!=null && baoCunBean.getZhujiDiZhi()!=null){
			Log.d(TAG, "onResume");
			try {
				String[] a1=baoCunBean.getZhujiDiZhi().split("//");
				String[] b1=a1[1].split(":");
				zhuji="http://"+b1[0];
				WebsocketPushMsg websocketPushMsg = new WebsocketPushMsg();
				websocketPushMsg.close();
				if (baoCunBean.getShipingIP() != null ) {
					websocketPushMsg.startConnection(baoCunBean.getZhujiDiZhi(), "rtsp://"+baoCunBean.getShipingIP()+":554/user=admin_password=tlJwpbo6_channel=1_stream=0.sdp?real_stream");
				}
			} catch (URISyntaxException e) {
				Log.d(TAG, e.getMessage()+"ddd");

			}
		}else {
			TastyToast.makeText(VlcVideoActivity.this,"请先设置主机地址和摄像头IP",TastyToast.LENGTH_SHORT,TastyToast.INFO).show();
		}
		sendBroadcast(new Intent(VlcVideoActivity.this,AlarmReceiver.class));
		super.onResume();
//		if (baoCunBean!=null && baoCunBean.getIsHengOrShu()!=isHX){
//			if (baoCunBean!=null && baoCunBean.getIsHengOrShu()){
//				isHX=true;
//				/**
//				 * 设置为横屏
//				 */
//				if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//				}
//
//			}else {
//				isHX=false;
//				/**
//				 * 设置为竖屏
//				 */
//				if(this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT){
//					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//				}
//			}
//		}




//		if (ijkVideoView!=null){
//			if (!ijkVideoView.isPlaying()){
//				ijkVideoView.start();
//			}
//		}

//		if (mediaPlayer==null){
//			Log.d(TAG, "onresume执行播放");
//
//			libvlc = LibVLCUtil.getLibVLC(VlcVideoActivity.this);
//			mediaPlayer = new MediaPlayer(libvlc);
//			vlcVout = mediaPlayer.getVLCVout();
//
//			callback = new IVLCVout.Callback() {
//				@Override
//				public void onNewLayout(IVLCVout ivlcVout, int i, int i1, int i2, int i3, int i4, int i5) {
//
//				}
//
//				@Override
//				public void onSurfacesCreated(IVLCVout ivlcVout) {
//					try {
//
//						changeSurfaceSize();
//
//					} catch (Exception e) {
//					  Log.d("vlc-newlayout", e.toString());
//					}
//				}
//
//				@Override
//				public void onSurfacesDestroyed(IVLCVout ivlcVout) {
//
//				}
//
//				@Override
//				public void onHardwareAccelerationError(IVLCVout vlcVout) {
//
//				}
//			};
//
//			vlcVout.addCallback(callback);
//			vlcVout.setVideoView(mSurfaceView);
//			vlcVout.attachViews();
//		}

	//	link_gengxing_erweima();
	}


	@Override
	public void onPause() {

		Log.d(TAG, "暂停");


		super.onPause();
	}

	@Override
	protected void onDestroy() {

		handler.removeCallbacksAndMessages(null);
		if (myReceiver!=null)
			unregisterReceiver(myReceiver);
		unregisterReceiver(netWorkStateReceiver);
		super.onDestroy();


//		if (mediaPlayer != null) {
//			mediaPlayer.stop();
//			mediaPlayer.release();
//			mediaPlayer=null;
//			vlcVout=null;
//			libvlc.release();
//		}

		//ijkVideoView.pause();
		//ijkVideoView.stopPlayback();
//		this.mSpeechSynthesizer.release();

		//unregisterReceiver(myReceiverFile);
	}

//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		setSurfaceSize(mVideoWidth, mVideoHeight, mVideoVisibleWidth, mVideoVisibleHeight, mSarNum, mSarDen);
//		super.onConfigurationChanged(newConfig);
//}

//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		if (mMediaPlayer != null) {
//			mSurfaceHolder = holder;
//			mMediaPlayer.attachSurface(holder.getSurface(), this);
//		}
//	}
//
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//		mSurfaceHolder = holder;
//		if (mMediaPlayer != null) {
//			mMediaPlayer.attachSurface(holder.getSurface(), this);//, width, height
//		}
//		if (width > 0) {
//			mVideoHeight = height;
//			mVideoWidth = width;
//		}
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		if (mMediaPlayer != null) {
//			mMediaPlayer.detachSurface();
//		}
//	}
//
//	@Override
//	public void setSurfaceSize(int width, int height, int visible_width, int visible_height, int sar_num, int sar_den) {
//
//		mVideoHeight = height;
//		mVideoWidth = width;
//		mVideoVisibleHeight = visible_height;
//		mVideoVisibleWidth = visible_width;
//		mSarNum = sar_num;
//		mSarDen = sar_den;
//		mHandler.removeMessages(HANDLER_SURFACE_SIZE);
//		mHandler.sendEmptyMessage(HANDLER_SURFACE_SIZE);
//	}

	//private static final int HANDLER_BUFFER_START = 1;
//	private static final int HANDLER_BUFFER_END = 2;
	//private static final int HANDLER_SURFACE_SIZE = 3;

//	private static final int SURFACE_BEST_FIT = 0;
//	private static final int SURFACE_FIT_HORIZONTAL = 1;
//	private static final int SURFACE_FIT_VERTICAL = 2;
//	private static final int SURFACE_FILL = 3;
//	private static final int SURFACE_16_9 = 4;
//	private static final int SURFACE_4_3 = 5;
	//private static final int SURFACE_ORIGINAL = 6;
//	private int mCurrentSize = SURFACE_BEST_FIT;

//	private Handler mVlcHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			if (msg == null || msg.getData() == null)
//				return;
//
//			switch (msg.getData().getInt("event")) {
//			case EventHandler.MediaPlayerTimeChanged:
//				break;
//			case EventHandler.MediaPlayerPositionChanged:
//				break;
//			case EventHandler.MediaPlayerPlaying:
//				mHandler.removeMessages(HANDLER_BUFFER_END);
//				mHandler.sendEmptyMessage(HANDLER_BUFFER_END);
//				break;
//			case EventHandler.MediaPlayerBuffering:
//				break;
//			case EventHandler.MediaPlayerLengthChanged:
//				break;
//			case EventHandler.MediaPlayerEndReached:
//				//播放完成
//				break;
//			}
//
//		}
//	};

//	private Handler mHandler = new Handler() {
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//			case HANDLER_BUFFER_START:
//                showLoading();
//				break;
//			case HANDLER_BUFFER_END:
//                hideLoading();
//				break;
//			case HANDLER_SURFACE_SIZE:
//				changeSurfaceSize();
//				break;
//			}
//		}
//	};


	private void changeSurfaceSize() {
		// get screen size
		int dw = Utils.getDisplaySize(getApplicationContext()).x;
		int dh = Utils.getDisplaySize(getApplicationContext()).y;
		//Log.d(TAG, "dw:" + dw);
		//Log.d(TAG, "dh:" + dh);
//		// calculate aspect ratio
//		double ar = (double) mVideoWidth / (double) mVideoHeight;
//		// calculate display aspect ratio
//		double dar = (double) dw / (double) dh;
//
//		switch (mCurrentSize) {
//		case SURFACE_BEST_FIT:
//			if (dar < ar)
//				dh = (int) (dw / ar);
//			else
//				dw = (int) (dh * ar);
//			break;
//		case SURFACE_FIT_HORIZONTAL:
//			dh = (int) (dw / ar);
//			break;
//		case SURFACE_FIT_VERTICAL:
//			dw = (int) (dh * ar);
//			break;
//		case SURFACE_FILL:
//			break;
//		case SURFACE_16_9:
//			ar = 16.0 / 9.0;
//			if (dar < ar)
//				dh = (int) (dw / ar);
//			else
//				dw = (int) (dh * ar);
//			break;
//		case SURFACE_4_3:
//			ar = 4.0 / 3.0;
//			if (dar < ar)
//				dh = (int) (dw / ar);
//			else
//				dw = (int) (dh * ar);
//			break;
//		case SURFACE_ORIGINAL:
//			dh = mVideoHeight;
//			dw = mVideoWidth;
//			break;
//		}
//		Log.d(TAG, "mVideoHeight:" + mVideoHeight);
//		Log.d(TAG, "(dh*3)/2:" + ((dh /3) * 2));
//		Log.d(TAG, "mVideoWidth:" + mVideoWidth);
	//	mSurfaceHolder.setFixedSize(mVideoWidth, mVideoHeight);

		//if (w<h){


		RelativeLayout.LayoutParams re1 = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();




		  re1.width=dw;
		  re1.height = (dh/3)*2-80;
		  re1.topMargin=dh/6-20;



		 recyclerView.setLayoutParams(re1);
		 recyclerView.invalidate();


//
//		}else {
//
//			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
//			lp.gravity= Gravity.TOP;
//
//			lp.width = (dw/2)+dip2px(context,30);
//			lp.height = dh-dip2px(this,90);
//			lp.topMargin=dip2px(this,30);
//			lp.leftMargin=dip2px(this,30);
//			linearLayout.setLayoutParams(lp);
//			linearLayout.invalidate();
//		}

		//mSurfaceView.setLayoutParams(lp);
		//mSurfaceView.invalidate();

	}
//	/**
//	 * websocket接口返回face.image
//	 * image为base64编码的字符串
//	 * 将字符串转为可以识别的图片
//	 * @param imgStr
//	 * @return
//	 */
//	public Bitmap generateImage(String imgStr, int cont, WBWeiShiBieDATABean dataBean, Context context) throws Exception {
//		// 对字节数组字符串进行Base64解码并生成图片
//		if (imgStr == null) // 图像数据为空
//			return null;
//		BASE64Decoder decoder = new BASE64Decoder();
//		try {
//			// Base64解码
//			final byte[][] b = {decoder.decodeBuffer(imgStr)};
//			for (int i = 0; i < b[0].length; ++i) {
//				if (b[0][i] < 0) {// 调整异常数据
//					b[0][i] += 256;
//				}
//			}
//			MoShengRenBean2 moShengRenBean2=new MoShengRenBean2();
//			moShengRenBean2.setId(dataBean.getTrack());
//			moShengRenBean2.setBytes(b[0]);
//			moShengRenBean2.setUrl("dd");
//
//			moShengRenBean2List.add(moShengRenBean2);
//
//				adapter.notifyDataSetChanged();
//
//
//
//
//
//			//   Bitmap bitmap= BitmapFactory.decodeByteArray(b[0],0, b[0].length);
//
//			//  Log.d("WebsocketPushMsg", "bitmap.getHeight():" + bitmap.getHeight());
//
//			// 生成jpeg图片
//			//  OutputStream out = new FileOutputStream(imgFilePath);
//			//   out.write(b);
//			//  out.flush();
//			//  out.close();
//
//
////			dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
////				@Override
////				public void onDismiss(DialogInterface dialog) {
////					Log.d("VlcVideoActivity", "Dialog销毁2");
////					b[0] =null;
////				}
////			});
//			//dialog.show();
//
//
//			return null;
//		} catch (Exception e) {
//			throw e;
//
//		}
//	}

	public  int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}
	public  int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale + 0.5f);
	}
	/**
	 * 识别消息推送
	 * 主机盒子端API ws://[主机ip]:9000/video
	 * 通过 websocket 获取 识别结果
	 * @author Wangshutao
	 */
	private class WebsocketPushMsg {
		/** * 识别消息推送
		 * @param wsUrl websocket接口 例如 ws://192.168.1.50:9000/video
		 * @param rtspUrl 视频流地址 门禁管理-门禁设备-视频流地址
		 *                例如 rtsp://192.168.0.100/live1.sdp
		 *                或者 rtsp://admin:admin12345@192.168.1.64/live1.sdp
		 *                或者 rtsp://192.168.1.103/user=admin&password=&channel=1&stream=0.sdp
		 *                或者 rtsp://192.168.1.100/live1.sdp
		 *                       ?__exper_tuner=lingyun&__exper_tuner_username=admin
		 *                       &__exper_tuner_password=admin&__exper_mentor=motion
		 *                       &__exper_levels=312,1,625,1,1250,1,2500,1,5000,1,5000,2,10000,2,10000,4,10000,8,10000,10
		 *                       &__exper_initlevel=6
		 * @throws URISyntaxException
		 * @throws
		 * @throws
		 *
		 *  ://192.168.2.52/user=admin&password=&channel=1&stream=0.sdp
		 *
		 *   rtsp://192.166.2.55:554/user=admin_password=tljwpbo6_channel=1_stream=0.sdp?real_stream
		 */
		private void startConnection(String wsUrl, String rtspUrl) throws URISyntaxException {
			//当视频流地址中出现&符号时，需要进行进行url编码
			if (rtspUrl.contains("&")){
				try {
					//Log.d("WebsocketPushMsg", "dddddttttttttttttttt"+rtspUrl);
					rtspUrl = URLEncoder.encode(rtspUrl,"UTF-8");

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					//Log.d("WebsocketPushMsg", e.getMessage());
				}
			}

			URI uri = URI.create(wsUrl + "?url=" + rtspUrl);
		//	Log.d("WebsocketPushMsg", "url="+uri);
			  webSocketClient = new WebSocketClient(uri) {
			//	private Vector vector=new Vector();

				@Override
				public void onOpen(ServerHandshake serverHandshake) {
					isLianJie=true;

				}

				@Override
				public void onMessage(String ss) {


					JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
					Gson gson=new Gson();
					WBBean wbBean= gson.fromJson(jsonObject, WBBean.class);

					if (wbBean.getType().equals("recognized")) { //识别
						Log.d("WebsocketPushMsg", "识别出了");

						final ShiBieBean dataBean = gson.fromJson(jsonObject, ShiBieBean.class);

							try {

								//mSpeechSynthesizer.speak("欢迎" + dataBean.getPerson().getName() + "来学校接送" + dataBean.getPerson().getRemark());
								MoShengRenBean bean = new MoShengRenBean(dataBean.getPerson().getId(), "sss");

								daoSession.insert(bean);

								Message message2 = Message.obtain();
								message2.arg1 = 1;
								message2.obj = dataBean.getPerson();
								handler.sendMessage(message2);


							}catch (Exception e){
								Log.d("WebsocketPushMsg", e.getMessage()+"aaa");
							}finally {
								try {
									Thread.sleep(300);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								try {
									daoSession.deleteByKey(dataBean.getPerson().getId());
								//	Log.d("WebsocketPushMsg", "删除");
								}catch (Exception e){
									Log.d("WebsocketPushMsg", e.getMessage()+"bbb");
								}
							}



					}
//             else if (wbBean.getType().equals("unrecognized")) {
//						Log.d("WebsocketPushMsg", "识别出了陌生人");
//
//						JsonObject jsonObject1 = jsonObject.get("data").getAsJsonObject();
//
//						final WeiShiBieBean dataBean = gson.fromJson(jsonObject1, WeiShiBieBean.class);
//
//
//						try {
//
//							MoShengRenBean bean = new MoShengRenBean(dataBean.getTrack(), "sss");
//						//	mSpeechSynthesizer.speak("欢迎你来访XX幼儿园");
//							daoSession.insert(bean);
//
//							if (isMoShengRen){
//								Message message = new Message();
//								message.arg1 = 2;
//								message.obj = dataBean;
//								handler.sendMessage(message);
//							}
//
//						} catch (Exception e) {
//							Log.d("WebsocketPushMsg", e.getMessage());
//							//e.printStackTrace();
//						}finally {
//							try {
//								Thread.sleep(300);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//							try {
//								daoSession.deleteByKey(dataBean.getTrack());
//								//Log.d("WebsocketPushMsg", "删除");
//							}catch (Exception e){
//								Log.d("WebsocketPushMsg", e.getMessage());
//							}
//						}
//					}
				}

				@Override
				public void onClose(int i, String s, boolean b) {
					isLianJie=false;

					//Log.d("WebsocketPushMsg", "onClose"+i);
					runOnUiThread( new Runnable() {
						@Override
						public void run() {
							TastyToast.makeText(VlcVideoActivity.this,"连接已断开,20秒后重新连接", Toast.LENGTH_LONG,TastyToast.ERROR).show();
						}
					});
//
//					if (conntionHandler==null && runnable==null){
//						Looper.prepare();
//						conntionHandler=new Handler();
//						runnable=new Runnable() {
//							@Override
//							public void run() {
//
//								Intent intent=new Intent("duanxianchonglian");
//								sendBroadcast(intent);
//							}
//						};
//						conntionHandler.postDelayed(runnable,13000);
//						Looper.loop();
//					}

				}

				@Override
				public void onError(Exception e) {
					Log.d("WebsocketPushMsg", "onError"+e.getMessage());

				}
			};

			webSocketClient.connect();
		}
		private void close(){
//
//			if (conntionHandler!=null && runnable!=null){
//				conntionHandler.removeCallbacks(runnable);
//				conntionHandler=null;
//				runnable=null;
//
//			}
			if (webSocketClient!=null){

//				if (webSocketClient.isOpen()){
//					webSocketClient.close();
//				}

				webSocketClient=null;
				System.gc();


			}

		}

	}



	private void creatUser(byte[] bytes, Long tt, String age) {
		//Log.d("WebsocketPushMsg", "创建用户");
		String fileName="tong"+System.currentTimeMillis()+".jpg";
		//通过bytes数组创建图片文件
		createFileWithByte(bytes,fileName,tt,age);
		//上传
	//	addPhoto(fileName);
	}

	/**
	 * 根据byte数组生成文件
	 *
	 * @param bytes
	 *            生成文件用到的byte数组
	 * @param age
	 */
	private void createFileWithByte(byte[] bytes, String filename, Long tt, String age) {
		/**
		 * 创建File对象，其中包含文件所在的目录以及文件的命名
		 */
		File file=null;
		String	sdDir = this.getFilesDir().getAbsolutePath();//获取跟目录
		makeRootDirectory(sdDir);

		// 创建FileOutputStream对象
		FileOutputStream outputStream = null;
		// 创建BufferedOutputStream对象
		BufferedOutputStream bufferedOutputStream = null;

		try {
			file = new File(sdDir +File.separator+ filename);
			// 在文件系统中根据路径创建一个新的空文件
		//	file2.createNewFile();
		//	Log.d(TAG, file.createNewFile()+"");

			// 获取FileOutputStream对象
			outputStream = new FileOutputStream(file);
			// 获取BufferedOutputStream对象
			bufferedOutputStream = new BufferedOutputStream(outputStream);
			// 往文件所在的缓冲输出流中写byte数据
			bufferedOutputStream.write(bytes);
			// 刷出缓冲输出流，该步很关键，要是不执行flush()方法，那么文件的内容是空的。
			bufferedOutputStream.flush();
			//上传文件
			addPhoto(sdDir,filename,bytes,tt,age);

		} catch (Exception e) {
			// 打印异常信息
			//Log.d(TAG, "ssssssssssssssssss"+e.getMessage());
		} finally {
			// 关闭创建的流对象
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bufferedOutputStream != null) {
				try {
					bufferedOutputStream.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public static void makeRootDirectory(String filePath) {
		File file = null;
		try {
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {

		}
	}

	private void addPhoto(final String path, final String fname, final byte[] b, final Long truck, final String age){

//		if (zhuji_string!=null){
//			String[] a=zhuji_string.split("//");
//			String[] b1=a[1].split(":");
//			zhuji="http://"+b1[0];
//		}

		OkHttpClient okHttpClient= new OkHttpClient();

         /* 第一个要上传的file */
		File file1 = new File(path,fname);
		RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream") , file1);
		final String file1Name = System.currentTimeMillis()+"testFile.jpg";

//    /* 第二个要上传的文件,这里偷懒了,和file1用的一个图片 */
//        File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/a.jpg");
//        RequestBody fileBody2 = RequestBody.create(MediaType.parse("application/octet-stream") , file2);
//        String file2Name = "testFile2.txt";


//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";

		MultipartBody mBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
            /* 底下是上传了两个文件 */
				.addFormDataPart("photo" , file1Name , fileBody1)
                  /* 上传一个普通的String参数 */
				//  .addFormDataPart("subject_id" , subject_id+"")
//                .addFormDataPart("file" , file2Name , fileBody2)
				.build();
		Request.Builder requestBuilder = new Request.Builder()
				// .header("Content-Type", "application/json")
				.post(mBody)
				.url(zhuji2+"/subject/photo");
		//    .url(HOST+"/mobile-admin/subjects");

		// step 3：创建 Call 对象
		Call call = okHttpClient.newCall(requestBuilder.build());
		//step 4: 开始异步请求
		final String finalZhuji = zhuji;
		call.enqueue(new Callback() {
			@Override
			public void onFailure(final Call call, final IOException e) {
			//	Log.d("AllConnects", "照片上传失败"+e.getMessage());

			}

			@Override
			public void onResponse(final Call call, Response response) throws IOException {
				Log.d("AllConnects", "照片上传成功"+call.request().toString());

				try{

				//获得返回体
				ResponseBody body = response.body();
				// Log.d("WebsocketPushMsg", "aa   "+response.body().string());
				JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
				Gson gson=new Gson();
				int code=jsonObject.get("code").getAsInt();
				if (code==0){
					JsonObject array=jsonObject.get("data").getAsJsonObject();
					TuPianBean zhaoPianBean=gson.fromJson(array,TuPianBean.class);
					//创建用户
				//	Log.d("VlcVideoActivity", "zhaoPianBean.getId():" + zhaoPianBean.getId());
					link(finalZhuji,"a",zhaoPianBean.getId()+"",b,age);

				}else {

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							TastyToast.makeText(getApplicationContext(),
									"图片不够清晰，请再来一张", TastyToast.LENGTH_LONG, TastyToast.ERROR);
						}
					});

				}
				//删除照片
				Log.d("VlcVideoActivity", "删除照片:" + VlcVideoActivity.this.deleteFile(fname));

				}catch (Exception e){
					Log.d("WebsocketPushMsg", e.getMessage());
				}
			}


		});


		}


	private void link(String zhuji, String name, String id, final byte[] b, final String age){

		final MediaType JSON=MediaType.parse("application/json; charset=utf-8");

		OkHttpClient okHttpClient= new OkHttpClient();

		List<Long> longs=new ArrayList<>();
		longs.add(Long.valueOf(id));
		ChuanJianUserBean bean=new ChuanJianUserBean();
		bean.setName(name);
		bean.setPhoto_ids(longs);
		bean.setSubject_type("0");

		String json = new Gson ().toJson(bean);
		RequestBody requestBody = RequestBody.create(JSON, json);


//		RequestBody body = new FormBody.Builder()
//				.add("name",name)
//				.add("subject_type",0+"")
//				.add("photo_ids","["+id+"]")
//				.build();
		Request.Builder requestBuilder = new Request.Builder()
				.header("Content-Type", "application/json")
				.post(requestBody)
				.url(zhuji2+"/subject");

		// step 3：创建 Call 对象
		Call call = okHttpClient.newCall(requestBuilder.build());

		//step 4: 开始异步请求
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.d("AllConnects", "请求失败"+e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				//Log.d("AllConnects", "请求成功"+call.request().toString());
				//获得返回体
				try{

				ResponseBody body = response.body();
				//  Log.d("AllConnects", "aa   "+response.body().string());

				JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
				Gson gson=new Gson();
				int code=jsonObject.get("code").getAsInt();
				if (code==0){
					JsonObject array=jsonObject.get("data").getAsJsonObject();
					User zhaoPianBean=gson.fromJson(array,User.class);
					link_houtai(zhaoPianBean);
					final MoShengRenBean2 moShengRenBean2 = new MoShengRenBean2();
					moShengRenBean2.setId(zhaoPianBean.getId());
					moShengRenBean2.setAge(age);
					moShengRenBean2.setBytes(b);
				//	moShengRenBean2.setUrl("http://192.168.2.7:8080/sign?cmd=signScan&subjectId="+zhaoPianBean.getId());



				}

				}catch (Exception e){
					Log.d("WebsocketPushMsg", e.getMessage());
				}

			}
		});


	}

	private void link_houtai(User zhaoPianBean) {
		//final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
		//http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
		OkHttpClient okHttpClient= new OkHttpClient();

		RequestBody body = new FormBody.Builder()
				.add("cmd","addSign")
				.add("subjectId",zhaoPianBean.getId()+"")
				.add("subjectPhoto",zhaoPianBean.getPhotos().get(0).getUrl())
				.build();
		Request.Builder requestBuilder = new Request.Builder()
				.header("Content-Type", "application/json")
				.post(body)
				.url("http://192.168.2.17:8080/sign");

		// step 3：创建 Call 对象
		Call call = okHttpClient.newCall(requestBuilder.build());

		//step 4: 开始异步请求
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				Log.d("AllConnects", "请求添加陌生人失败"+e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				Log.d("AllConnects", "请求添加陌生人成功"+call.request().toString());
				//获得返回体
				try {

				ResponseBody body = response.body();
			//	Log.d("AllConnects", "aa   "+response.body().string());

				JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
				Gson gson=new Gson();
				int code=jsonObject.get("resultCode").getAsInt();
				if (code==0){
//					JsonObject array=jsonObject.get("data").getAsJsonObject();
//					User zhaoPianBean=gson.fromJson(array,User.class);
//					link_houtai(zhaoPianBean);
					//link_gengxing_erweima();
				}

				}catch (Exception e){
					Log.d("WebsocketPushMsg", e.getMessage());
				}
			}
		});


		}

	public class NetWorkStateReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			//检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
			if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

				//获得ConnectivityManager对象
				ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

				//获取ConnectivityManager对象对应的NetworkInfo对象
				//以太网
				NetworkInfo wifiNetworkInfo1 = connMgr.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
				//获取WIFI连接的信息
				NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				//获取移动数据连接的信息
				NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				if (wifiNetworkInfo1.isConnected() || wifiNetworkInfo.isConnected() || dataNetworkInfo.isConnected()){
					wangluo.setVisibility(View.GONE);

				}else {
					isLianJie=false;

					wangluo.setVisibility(View.VISIBLE);
				}


//				if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
//					Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
//				} else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
//					Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
//				} else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
//					Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
//				} else {
//					Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show();
//				}
//API大于23时使用下面的方式进行网络监听
			}else {

				Log.d(TAG, "API23");
				//获得ConnectivityManager对象
				ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

				//获取所有网络连接的信息
				Network[] networks = connMgr.getAllNetworks();
				//用于存放网络连接信息
				StringBuilder sb = new StringBuilder();
				//通过循环将网络信息逐个取出来
				Log.d(TAG, "networks.length:" + networks.length);
				if (networks.length==0){
					isLianJie=false;
					wangluo.setVisibility(View.VISIBLE);
				}
				for (int i=0; i < networks.length; i++){
					//获取ConnectivityManager对象对应的NetworkInfo对象
					NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);

					if (networkInfo.isConnected()){
						wangluo.setVisibility(View.GONE);

					}
				}

			}
		}
	}

//	private class DownloadReceiver extends ResultReceiver {
//		public DownloadReceiver(Handler handler) {
//			super(handler);
//		}
//		@Override
//		protected void onReceiveResult(int resultCode, Bundle resultData) {
//			super.onReceiveResult(resultCode, resultData);
//			if (resultCode == DownloadService.UPDATE_PROGRESS) {
//				String ididid=resultData.getString("ididid2");
//				int progress = resultData.getInt("progress");
//
//				if (progress == 100) {
//					try {
//
//						//下载完成
//						//更新状态
//						Log.d(TAG, "ididididididd值："+ididid);
//						if (ididid!=null) {
//							ShiPingBean b = shiPingBeanDao.load(ididid);
//							b.setIsDownload(true);
//							shiPingBeanDao.update(b);
//
//							if (shiPingBeanList.size() > 0) {
//								shiPingBeanList.clear();
//							}
//							shiPingBeanList = shiPingBeanDao.loadAll();
//
//							ijkVideoView.setVideoPath(Environment.getExternalStorageDirectory() + File.separator + "linhefile" + File.separator + b.getId() + "." + b.getVideoType().split("\\/")[1]);
//							ijkVideoView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
//								@Override
//								public void onPrepared(IMediaPlayer iMediaPlayer) {
//									ijkVideoView.start();
//								}
//							});
//						}else {
//							Log.d(TAG, "id的值是空");
//						}
//
//					}catch (Exception e){
//						Log.d(TAG, "捕捉到异常onReceiveResult"+e.getMessage());
//					}
//
//					//ijkVideoView.setVideoPath(mFile.getPath());
//					//ijkVideoView.start();
////					Intent install = new Intent();
////					install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////					install.setAction(android.content.Intent.ACTION_VIEW);
////					install.setDataAndType(Uri.fromFile(mFile),"application/vnd.android.package-archive");
////					startActivity(install);  //下载完成打开APK
//				}
//			}
//		}
//	}

//	private class DownloadReceiverTuPian extends ResultReceiver {
//		public DownloadReceiverTuPian(Handler handler) {
//			super(handler);
//		}
//		@Override
//		protected void onReceiveResult(int resultCode, Bundle resultData) {
//			super.onReceiveResult(resultCode, resultData);
//			if (resultCode == DownloadTuPianService.UPDATE_PROGRESS2) {
//				int progress = resultData.getInt("progress");
//
//				if (progress == 100) {
//					try {
//						//下载完成
//						// Environment.getExternalStorageDirectory()+ File.separator+"linhefile"+File.separator+"tupian111.jpg"
//						Log.d(TAG, "图片下载完成");
//
//					}catch (Exception e){
//						Log.d(TAG, "捕捉到异常onReceiveResult"+e.getMessage());
//					}
//
//				}
//			}
//		}
//	}

//	public static final int TIMEOUT = 1000 * 60;
//	private void link_chengshi() {
//		//final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//		//http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
//		OkHttpClient okHttpClient= new OkHttpClient.Builder()
//				.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.retryOnConnectionFailure(true)
//				.build();
//
////		RequestBody body = new FormBody.Builder()
////				.add("cityCode","101040100")
////				.add("weatherType","1")
////				.build();
//
//		Request.Builder requestBuilder = new Request.Builder()
//				//.header("Content-Type", "application/json")
//				.get()
//				.url("http://api.map.baidu.com/location/ip?ak=uTTmEt0NeHSsgAKsXGLAMC8mvg6zPNLm" +
//						"&mcode=21:21:DA:F2:00:51:3B:AB:C4:E6:19:18:31:C6:98:CA:D6:7B:44:AE;com.example.shidianxiaoping");
//		//.url("http://wthrcdn.etouch.cn/weather_mini?city=广州市");
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				Log.d("AllConnects", "请求添加陌生人失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				Log.d("AllConnects", "请求添加陌生人成功"+call.request().toString());
//				//获得返回体
//				try {
//
//					ResponseBody body = response.body();
//				//	Log.d("AllConnects", "aa   "+response.body().string());
//
//					JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
//					Gson gson=new Gson();
//				//	JsonObject object=jsonObject.get("ContentBean").getAsJsonObject();
//
//					IpAddress zhaoPianBean=gson.fromJson(jsonObject,IpAddress.class);
//
//
//					/**从assets中读取txt
//					 * 按行读取txt
//					 * @param
//					 * @return
//					 * @throws Exception
//					 */
//
//						InputStream is = getAssets().open("tianqi.txt");
//						InputStreamReader reader = new InputStreamReader(is);
//						BufferedReader bufferedReader = new BufferedReader(reader);
//						//StringBuffer buffer = new StringBuffer("");
//						String str;
//						String aa=zhaoPianBean.getContent().getAddress_detail().getCity();
//						if (aa.length()>2){
//							aa=aa.substring(0,2);
//						//	Log.d("VlcVideoActivity", "fffff9"+aa);
//						}
//						while ((str = bufferedReader.readLine()) != null) {
//
//
//							if (str.contains(aa)){
//								//Log.d("VlcVideoActivity", "fffff3"+str);
//								link_tianqi(str);
//								break;
//							}
//						}
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//			}
//		});
//
//
//	}

//	private void link_tianqi(String bean) {
//		//final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
//		//http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
//		OkHttpClient okHttpClient= new OkHttpClient.Builder()
//				.writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//				.retryOnConnectionFailure(true)
//				.build();
////		RequestBody body = new FormBody.Builder()
////				.add("cityCode","101040100")
////				.add("weatherType","1")
////				.build();
//
//		Request.Builder requestBuilder = new Request.Builder()
//				//.header("Content-Type", "application/json")
//				.get()
//				//.url("https://api.map.baidu.com/location/ip?ak=uTTmEt0NeHSsgAKsXGLAMC8mvg6zPNLm" +
//					//	"&mcode=21:21:DA:F2:00:51:3B:AB:C4:E6:19:18:31:C6:98:CA:D6:7B:44:AE;com.example.shidianxiaoping");
//
//				.url("http://wthrcdn.etouch.cn/weather_mini?citykey=" + bean.substring(bean.length() - 9, bean.length()));
//
//		// step 3：创建 Call 对象
//		Call call = okHttpClient.newCall(requestBuilder.build());
//
//		//step 4: 开始异步请求
//		call.enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//				Log.d("AllConnects", "请求添加陌生人失败"+e.getMessage());
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//				Log.d("AllConnects", "请求天气成功"+call.request().toString());
//				//获得返回体
//				try {
//
//					ResponseBody body = response.body();
//					//Log.d("AllConnects", "aa   "+response.body().string());
//
//					JsonObject jsonObject= GsonUtil.parse(body.string()).getAsJsonObject();
//					Gson gson=new Gson();
//					//JsonObject object=jsonObject.get("ContentBean").getAsJsonObject();
//
//					final TianQiBean zhaoPianBean=gson.fromJson(jsonObject,TianQiBean.class);
//					runOnUiThread(new Runnable() {
//						@Override
//						public void run() {
//							tianqi0.setText(zhaoPianBean.getData().getCity());
//							tianqi1.setText(zhaoPianBean.getData().getWendu()+" 度");
//						//	tianqi2.setText(zhaoPianBean.getData().getGanmao());
//						}
//					});
//
//
//				}catch (Exception e){
//					Log.d("WebsocketPushMsg", e.getMessage());
//				}
//			}
//		});
//
//
//	}

}
