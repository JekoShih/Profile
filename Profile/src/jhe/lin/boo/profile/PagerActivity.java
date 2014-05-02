/*
 * Copyright (c) 2012 Wireless Designs, LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package jhe.lin.boo.profile;



import com.Animation.ZoomOutPageTransformer;
import com.WH.WH;

import jhe.lin.boo.profile.R;
import jhe.lin.boo.profile.view.CircleView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

/**
 * PagerActivity: A Sample Activity for PagerContainer
 */
public class PagerActivity extends Activity {
	private final int item[]={R.string.item1,R.string.item2,R.string.item3,R.string.item4,R.string.item5};
	public static final String DEFAULT="#FFDCDCDC";
	public static final String SELECTED="#FF696969";
	public static final int firstPage=0;
    ViewPager pager;
    TextView title;
    AudioManager audioManager;
    private LinearLayout pager_bar;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WH.getDisplayMetrics(this);
        
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        
        pager_bar = (LinearLayout)findViewById(R.id.pager_bar);
        LayoutParams ll=(LayoutParams)pager_bar.getLayoutParams();
        ll.topMargin=WH.getHeightPercent(70);
        pager_bar.setLayoutParams(ll);
        
        title = (TextView)findViewById(R.id.title);
        ll=(LayoutParams)title.getLayoutParams();
        ll.topMargin=WH.getHeightPercent(10);
        title.setLayoutParams(ll);
        
        pager = (ViewPager)findViewById(R.id.pager);
        title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/w1.otf"));
        initPages_bar();
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);
        pager.setCurrentItem(firstPage);
        pager.setOnPageChangeListener(new OnPageChangeListener(){
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				for(int i=0;i<item.length;i++)
				{
					CircleView cv=(CircleView)pager_bar.getChildAt(i);
					cv.setColor(Color.parseColor(DEFAULT));
				}
				CircleView cv=(CircleView)pager_bar.getChildAt(pager.getCurrentItem());
				cv.setColor(Color.parseColor(SELECTED));
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
        });
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
        pager.setPageMargin(15);

        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);

    }
    
	private void initPages_bar()
	{
		for(int i=0;i<item.length;i++)
		{
			LinearLayout.LayoutParams l=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			l.weight=0.1f;
			CircleView cv=new CircleView(PagerActivity.this);
			if(i==firstPage)
				cv.setColor(Color.parseColor(SELECTED));
			else
				cv.setColor(Color.parseColor(DEFAULT));
			cv.setId(5000+i);
			cv.setLayoutParams(l);
			pager_bar.addView(cv);
			
		}
	}
    
    //Nothing special about this adapter, just throwing up colored views for demo
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
        	RelativeLayout root=new RelativeLayout(PagerActivity.this);
        	LayoutParams l=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        	root.setLayoutParams(l);
            Button view = new Button(PagerActivity.this);
            l=new LayoutParams(WH.getWeightPercent(50),WH.getHeightPercent(30));
            l.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            view.setLayoutParams(l);
            view.setText(item[position]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundResource(R.drawable.selector1);
            view.setGravity(Gravity.CENTER);
            view.setOnClickListener(new View.OnClickListener() 
            {
                public void onClick(View v) 
                {
                     audioManager.playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN);   
                }
            });
            root.addView(view);
            //view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));

            container.addView(root);
            return root;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
            //this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return item.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
}