package es.carlosrolindez.lallanyta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	public PictureCollection pictureCollection = new PictureCollection();
	public static Vibrator vib;
	public static final SoundPool sp = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
	public static int okSound;
	public static int tachanSound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		okSound     = sp.load(this, R.raw.tata,  1); 
		tachanSound = sp.load(this, R.raw.tachan,  1); 
		
		setContentView(R.layout.activity_main);
	    findViewById(R.id.imagetopleft1).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagetopleft2).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagetopleft3).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagetopleft4).setOnTouchListener(new ImageTouchListener());

	    findViewById(R.id.imagetopright1).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagetopright2).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagetopright3).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagetopright4).setOnTouchListener(new ImageTouchListener());
	    
	    findViewById(R.id.imagebottomleft1).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagebottomleft2).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagebottomleft3).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagebottomleft4).setOnTouchListener(new ImageTouchListener());

	    findViewById(R.id.imagebottomright1).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagebottomright2).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagebottomright3).setOnTouchListener(new ImageTouchListener());
	    findViewById(R.id.imagebottomright4).setOnTouchListener(new ImageTouchListener());
	    
	    

	    findViewById(R.id.topleft).setOnDragListener(new GridDragListener(getResources().getDrawable(R.drawable.purple_bg) ,
	    		getResources().getDrawable(R.drawable.purple_selected_bg),"purple"));
	    findViewById(R.id.topright).setOnDragListener(new GridDragListener(getResources().getDrawable(R.drawable.blue_bg) ,
	    		getResources().getDrawable(R.drawable.blue_selected_bg),"blue"));
	    findViewById(R.id.bottomleft).setOnDragListener(new GridDragListener(getResources().getDrawable(R.drawable.green_bg) ,
	    		getResources().getDrawable(R.drawable.green_selected_bg),"green"));
	    findViewById(R.id.bottomright).setOnDragListener(new GridDragListener(getResources().getDrawable(R.drawable.orange_bg) ,
	    		getResources().getDrawable(R.drawable.orange_selected_bg),"orange"));

	    findViewById(R.id.imagetopleft1).setOnDragListener(new ImageDragListener(R.id.imagetopleft1));
	    findViewById(R.id.imagetopleft2).setOnDragListener(new ImageDragListener(R.id.imagetopleft2));
	    findViewById(R.id.imagetopleft3).setOnDragListener(new ImageDragListener(R.id.imagetopleft3));
	    findViewById(R.id.imagetopleft4).setOnDragListener(new ImageDragListener(R.id.imagetopleft4));

	    findViewById(R.id.imagetopright1).setOnDragListener(new ImageDragListener(R.id.imagetopright1));
	    findViewById(R.id.imagetopright2).setOnDragListener(new ImageDragListener(R.id.imagetopright2));
	    findViewById(R.id.imagetopright3).setOnDragListener(new ImageDragListener(R.id.imagetopright3));
	    findViewById(R.id.imagetopright4).setOnDragListener(new ImageDragListener(R.id.imagetopright4));
	    
	    findViewById(R.id.imagebottomleft1).setOnDragListener(new ImageDragListener(R.id.imagebottomleft1));
	    findViewById(R.id.imagebottomleft2).setOnDragListener(new ImageDragListener(R.id.imagebottomleft2));
	    findViewById(R.id.imagebottomleft3).setOnDragListener(new ImageDragListener(R.id.imagebottomleft3));
	    findViewById(R.id.imagebottomleft4).setOnDragListener(new ImageDragListener(R.id.imagebottomleft4));

	    findViewById(R.id.imagebottomright1).setOnDragListener(new ImageDragListener(R.id.imagebottomright1));
	    findViewById(R.id.imagebottomright2).setOnDragListener(new ImageDragListener(R.id.imagebottomright2));
	    findViewById(R.id.imagebottomright3).setOnDragListener(new ImageDragListener(R.id.imagebottomright3));
	    findViewById(R.id.imagebottomright4).setOnDragListener(new ImageDragListener(R.id.imagebottomright4));
	    
		pictureCollection.updateDrawing(); 
	   
	}
	
	public class PictureCollection {
		
		public final List<Integer> imageViewId = Arrays.asList(
			R.id.imagetopleft1, R.id.imagetopleft2, R.id.imagetopleft3, R.id.imagetopleft4,
			R.id.imagetopright1, R.id.imagetopright2, R.id.imagetopright3, R.id.imagetopright4,
			R.id.imagebottomleft1, R.id.imagebottomleft2, R.id.imagebottomleft3, R.id.imagebottomleft4,
			R.id.imagebottomright1, R.id.imagebottomright2, R.id.imagebottomright3, R.id.imagebottomright4 ); 

	    public List<Integer> drawableId;
	    public List<Integer> patternId;
		
	    
		public PictureCollection() {
			drawableId = Arrays.asList(
		    		(Integer)R.drawable.julia1,(Integer)R.drawable.julia2,(Integer)R.drawable.julia3,(Integer) 0,
		    		(Integer)R.drawable.pilar1,(Integer)R.drawable.pilar2,(Integer)R.drawable.pilar3,(Integer) 0,
		    		(Integer)R.drawable.lucia1,(Integer)R.drawable.lucia2,(Integer)R.drawable.lucia3,(Integer) 0,
		    		(Integer)R.drawable.chus1, (Integer)R.drawable.chus2, (Integer)R.drawable.chus3, (Integer) 0);
			patternId = Arrays.asList(
		    		(Integer)R.drawable.pilar1,(Integer)R.drawable.pilar2,(Integer)R.drawable.pilar3,
		    		(Integer)R.drawable.lucia1,(Integer)R.drawable.lucia2,(Integer)R.drawable.lucia3,
		    		(Integer)R.drawable.julia1,(Integer)R.drawable.julia2,(Integer)R.drawable.julia3,
		    		(Integer)R.drawable.chus1, (Integer)R.drawable.chus2, (Integer)R.drawable.chus3);
		    Collections.shuffle(drawableId);
		}
		
		public void updateDrawing() {
			for (Integer image :  imageViewId)
			{
				int pos = imageViewId.indexOf((Integer)image);
				if (drawableId.get(pos) > 0 ) {
					((ImageView) findViewById(image)).setImageDrawable(getResources().getDrawable(drawableId.get(pos)));
//					((ImageView) findViewById(image)).setVisibility(View.VISIBLE);
					((ImageView) findViewById(image)).invalidate();
				}
				else {
					((ImageView) findViewById(image)).setImageDrawable(null);
//					((ImageView) findViewById(image)).setVisibility(View.INVISIBLE);	
					((ImageView) findViewById(image)).invalidate();
				}


			}
		}

		public boolean changeDrawing(int image, int drawable) {
			int pos = imageViewId.indexOf((Integer)image);
			if ( pos < 0 ) return false;
			if (drawable == 0) return false;
	/*		if (drawableId.get(pos) == drawable) {
				Log.e("chageDrawing","change Mind");
				((ImageView) findViewById(image)).setVisibility(View.VISIBLE);
				return true;
			}*/
			if (drawableId.get(pos) > 0) return false;
			
			drawableId.set(pos, drawable);	
			((ImageView) findViewById(image)).setImageDrawable(getResources().getDrawable(drawable));
//			((ImageView) findViewById(image)).setVisibility(View.VISIBLE);

			
			for (Integer imageSet :  imageViewId)
			{
				pos = imageViewId.indexOf((Integer)imageSet);
				final ImageView droppedView =  (ImageView) findViewById(imageSet);
				
				if (droppedView.getVisibility() == View.INVISIBLE) {
					drawableId.set(pos,0);		
					droppedView.setImageDrawable(null);
					droppedView.post(new Runnable() {
						public void run() {
							droppedView.setVisibility(View.VISIBLE);
						}
					});	
					droppedView.invalidate();
					break;
				}
			}
			
			return true;
		
		}
		

		public int hideDrawing(int image) {
			int pos = imageViewId.indexOf((Integer)image);
			if ( pos < 0 ) return 0;
			if (drawableId.get(pos) == 0) return 0;

			final ImageView droppedView =  (ImageView) findViewById(image);
			droppedView.post(new Runnable() {
				public void run() {
					droppedView.setVisibility(View.INVISIBLE);
				}
			});	
			
//			((ImageView) findViewById(image)).setVisibility(View.INVISIBLE);
			return drawableId.get(pos);
		}
		
		
		public void showDrawing(int drawable) {
			int pos = drawableId.indexOf((Integer)drawable);
			final ImageView droppedView =  (ImageView) findViewById(imageViewId.get(pos));

			droppedView.post(new Runnable() {
				public void run() {
					droppedView.setVisibility(View.VISIBLE);
				}
			});	
		}
		
		public boolean checkImage(int image, int drawable) {
			
			int posDrawable = patternId.indexOf(drawable);
			int gridDrawable = posDrawable/3;
			
			int posImage = imageViewId.indexOf(image);
			int gridImage = posImage/4;
			
			return (gridDrawable==gridImage);
			
		}

		public boolean checkPattern() {
			for (int grid = 0; grid<4 ; grid++) 
			{
				for (int imageGrid = 0;  imageGrid<4; imageGrid++)
				{
					Integer pattern = drawableId.get(imageGrid + grid*4);
					int pos = patternId.indexOf(pattern);
					if (pos < 0) continue;
					if (pos >= (grid+1)*3) return false;
					if (pos < (grid*3)) return false;
				}
			}
			return true;			
		}

			
	}

	
	public final class ImageTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				int drawableId = pictureCollection.hideDrawing(view.getId());
				if (drawableId > 0) {
					ClipData data = ClipData.newPlainText("", "");
					DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
					view.startDrag(data, shadowBuilder, drawableId, 0);
				}
				return true;
			} else {
				return false;
			}
		}
	}

	class GridDragListener implements OnDragListener {
	    Drawable enterShape = getResources().getDrawable(R.drawable.blue_selected_bg);
	    Drawable normalShape = getResources().getDrawable(R.drawable.blue_bg);
	    String TAG;

	    public GridDragListener(Drawable normal, Drawable selected,String color) {
	    	enterShape = selected;
	    	normalShape = normal;
	    	TAG = color;

	    }
	    
	    @Override
	    public boolean onDrag(View v, DragEvent event) {
//	    	int action = event.getAction();
	    	switch (event.getAction()) {
	    	case DragEvent.ACTION_DRAG_STARTED:
	    		// do nothing
	    		break;
	    	case DragEvent.ACTION_DRAG_ENTERED:
	    		v.setBackgroundDrawable(enterShape);
	    		break;
	    	case DragEvent.ACTION_DRAG_EXITED:
	    		vib.vibrate(200);
	    		v.setBackgroundDrawable(normalShape);
	    		break;
	    	case DragEvent.ACTION_DROP:
	    		v.setBackgroundDrawable(normalShape);
	    		return false;
	    	case DragEvent.ACTION_DRAG_ENDED:
	    		v.setBackgroundDrawable(normalShape);
	    	default:
	    		break;
	    	}
	    	return true;
	    }
	}

	class ImageDragListener implements OnDragListener {
	    int localImageId;

	    public ImageDragListener(int imageId) {
	    	localImageId = imageId;

	    }
    
	    @Override
	    public boolean onDrag(View v, DragEvent event) {
	    	switch (event.getAction()) {
	    	case DragEvent.ACTION_DRAG_STARTED:
	    		break;
	    	case DragEvent.ACTION_DRAG_ENTERED:
	    		break;
	    	case DragEvent.ACTION_DRAG_EXITED:
	    		break;
	    	case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup
				Integer drawableId = (Integer) event.getLocalState();
	    		if (pictureCollection.changeDrawing(localImageId, drawableId)) {
	    			if (pictureCollection.checkPattern()) {
		        		sp.play(tachanSound, 1, 1, 0, 0, 1);    				
	    			}
	    			else if (pictureCollection.checkImage(localImageId, drawableId)) {
		        		sp.play(okSound, 1, 1, 0, 0, 1);	
	    			}
	    		}
				return false;
	    	case DragEvent.ACTION_DRAG_ENDED:
				Integer drawable = (Integer) event.getLocalState();
	    		pictureCollection.showDrawing(drawable);
	    		return true;
	    	default:	    		
	    		break;
	    	}
	    	return true;
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
