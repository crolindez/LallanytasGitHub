package es.carlosrolindez.lallanyta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
	
	GridContainer[] gridSet = new GridContainer[4];
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    findViewById(R.id.imagetopleft1).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagetopleft2).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagetopleft3).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagetopleft4).setOnTouchListener(new MyTouchListener());

	    findViewById(R.id.imagetopright1).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagetopright2).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagetopright3).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagetopright4).setOnTouchListener(new MyTouchListener());
	    
	    findViewById(R.id.imagebottomleft1).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagebottomleft2).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagebottomleft3).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagebottomleft4).setOnTouchListener(new MyTouchListener());

	    findViewById(R.id.imagebottomright1).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagebottomright2).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagebottomright3).setOnTouchListener(new MyTouchListener());
	    findViewById(R.id.imagebottomright4).setOnTouchListener(new MyTouchListener());
	    
	    
	    gridSet[0] = new GridContainer(R.id.imagetopleft1,R.id.imagetopleft2,R.id.imagetopleft3,R.id.imagetopleft4);
	    gridSet[1] = new GridContainer(R.id.imagetopright1,R.id.imagetopright2,R.id.imagetopright3,R.id.imagetopright4);
	    gridSet[2] = new GridContainer(R.id.imagebottomleft1,R.id.imagebottomleft2,R.id.imagebottomleft3,R.id.imagebottomleft4);
	    gridSet[3] = new GridContainer(R.id.imagebottomright1,R.id.imagebottomright2,R.id.imagebottomright3,R.id.imagebottomright4);

	    findViewById(R.id.topleft).setOnDragListener(new MyDragListener(getResources().getDrawable(R.drawable.purple_bg) ,
	    		getResources().getDrawable(R.drawable.purple_selected_bg),"purple",gridSet[0]));
	    findViewById(R.id.topright).setOnDragListener(new MyDragListener(getResources().getDrawable(R.drawable.blue_bg) ,
	    		getResources().getDrawable(R.drawable.blue_selected_bg),"blue",gridSet[1]));
	    findViewById(R.id.bottomleft).setOnDragListener(new MyDragListener(getResources().getDrawable(R.drawable.green_bg) ,
	    		getResources().getDrawable(R.drawable.green_selected_bg),"green",gridSet[2]));
	    findViewById(R.id.bottomright).setOnDragListener(new MyDragListener(getResources().getDrawable(R.drawable.orange_bg) ,
	    		getResources().getDrawable(R.drawable.orange_selected_bg),"orange",gridSet[3]));
	    
	    List<Integer> list = Arrays.asList(
	    		(Integer)R.drawable.julia1,(Integer)R.drawable.julia2,(Integer)R.drawable.julia3,
	    		(Integer)R.drawable.pilar1,(Integer)R.drawable.pilar2,(Integer)R.drawable.pilar3,
	    		(Integer)R.drawable.lucia1,(Integer)R.drawable.lucia2,(Integer)R.drawable.lucia3,
	    		(Integer)R.drawable.chus1,(Integer)R.drawable.chus2,(Integer)R.drawable.chus3);
	    		
	    		
	    Collections.shuffle(list);

	    int imageCont = 0;
	    for (int i = 0; i < 4; i++) {
	    	gridSet[i].addPicture(getResources().getDrawable(list.get(imageCont++)));
	    	gridSet[i].addPicture(getResources().getDrawable(list.get(imageCont++)));	    	
	    	gridSet[i].addPicture(getResources().getDrawable(list.get(imageCont++)));
	    }

	}

	public class GridContainer {
		
		int numPictures;
		int[] picturesID = new int[4];
		
		
		public GridContainer(int res0, int res1, int res2, int res3) {
			numPictures = 0;
			picturesID[0] = res0;
			picturesID[1] = res1;
			picturesID[2] = res2;
			picturesID[3] = res3;
			
		}
		
		public int size() {
			int size = 0;
			for (int picture: picturesID) {
				if (((ImageView) findViewById(picture)).getDrawable() != null)  size++;
			}
			
			return size;
		}
		
		public boolean addPicture(Drawable draw) {
			for (int picture: picturesID) {
				if (((ImageView) findViewById(picture)).getDrawable() == null)  {
					((ImageView) findViewById(picture)).setImageDrawable(draw);
					((ImageView) findViewById(picture)).invalidate();
					return true;
				}
			}
			return false;
			
		}
		public boolean removePicture(int id) {
			Log.e("removePicture",""+id);
			for (int picture: picturesID) {
				Log.e("picture",""+picture );
				if (picture == id)  {
					((ImageView) findViewById(picture)).setImageDrawable(null);
					((ImageView) findViewById(picture)).invalidate();
					return true;
				}
			}
			return false;
		}
	}
	
	private final class MyTouchListener implements OnTouchListener {
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
				view.startDrag(data, shadowBuilder, ((ImageView) view).getDrawable(), 0);
	    		for (GridContainer item : gridSet ) item.removePicture(view.getId());
//				view.setVisibility(View.INVISIBLE);
				return true;
			} else {
				return false;
			}
		}
	}

	class MyDragListener implements OnDragListener {
	    Drawable enterShape = getResources().getDrawable(R.drawable.blue_selected_bg);
	    Drawable normalShape = getResources().getDrawable(R.drawable.blue_bg);
	    String TAG;
	    GridContainer grid;

	    public MyDragListener(Drawable normal, Drawable selected,String color, GridContainer gr) {
	    	enterShape = selected;
	    	normalShape = normal;
	    	TAG = color;
	    	grid = gr;
	    }
	    
	    @Override
	    public boolean onDrag(View v, DragEvent event) {
//	    	int action = event.getAction();
	    	switch (event.getAction()) {
	    	case DragEvent.ACTION_DRAG_STARTED:
	    		// do nothing
	    		Log.e("Drag " + TAG,"started");
	    		break;
	    	case DragEvent.ACTION_DRAG_ENTERED:
	    		Log.e("Drag " + TAG,"entered");
	    		v.setBackgroundDrawable(enterShape);
	    		break;
	    	case DragEvent.ACTION_DRAG_EXITED:
	    		Log.e("Drag " + TAG,"exited");
	    		v.setBackgroundDrawable(normalShape);
	    		break;
	    	case DragEvent.ACTION_DROP:
				// Dropped, reassign View to ViewGroup
	    		Log.e("Drag " + TAG,"drop");
				Drawable draw = (Drawable) event.getLocalState();
	    		grid.addPicture(draw);
	/*			ViewGroup owner = (ViewGroup) view.getParent();
				owner.removeView(view);
				LinearLayout container = (LinearLayout) v;
				container.addView(view);
				view.setVisibility(View.VISIBLE);*/
				break;
	    	case DragEvent.ACTION_DRAG_ENDED:
	    		Log.e("Drag " + TAG,"ended");
	    		v.setBackgroundDrawable(normalShape);
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
