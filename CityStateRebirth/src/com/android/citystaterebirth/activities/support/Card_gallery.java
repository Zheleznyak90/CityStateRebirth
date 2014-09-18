package com.android.citystaterebirth.activities.support;

import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.citystaterebirth.R;
import com.android.citystaterebirth.activities.gameCircle.Role_choosing;
import com.android.citystaterebirth.functions.ListScrollGesture;

public class Card_gallery extends Fragment {
	private String inpModifier;
	//private String[] inpIdArr;
	private ArrayList<String> inpIdsArr;
	
	private int ID_ARRAY_SIZE;
	
	private int currElem = 0;
	private RelativeLayout cardShowdRL;
	private View fragmentView;
	private LinkedList<ImageView> visibleImg;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		fragmentView = inflater.inflate(R.layout.card_gallery, null);
		cardShowdRL = (RelativeLayout) fragmentView.findViewById(R.id.cardShowFragmRL);
		
		Bundle picIds = this.getArguments();
		visibleImg = new LinkedList<ImageView>();
		
		if(picIds != null){
			inpModifier = picIds.getString("Modifier");
			inpIdsArr = picIds.getStringArrayList("Ids");
			ID_ARRAY_SIZE = inpIdsArr.size();
			
			fillList();
			refreshDisplay(true);
		}
		
		fragmentView.setOnTouchListener(new ListScrollGesture(getActivity()) {
            public void onSwipeRight() {//Движение слева направо - прокрутка списка назад
            	if(currElem>=1){
            		swipeRightDisplay(currElem);
            		currElem--;
            	}
            }
            public void onSwipeLeft() {
            	if(currElem<=ID_ARRAY_SIZE){//Движение справа налево - прокрутка списка вперед
            		swipeLeftDisplay(currElem);
            		currElem++;
            	}
            }
		});
		return fragmentView;
	  }
	
	private ImageView createImgView(Context _cont, int _currElem){//добавление карты в фрагмент
		ImageView tempImage;
		Drawable tmpDraw;
		int drawableRes;
		
		tempImage = new ImageView(_cont);
		drawableRes = getResources().getIdentifier(inpModifier+inpIdsArr.get(_currElem), "drawable", _cont.getPackageName());
		
		tmpDraw = getResources().getDrawable(drawableRes);
		tempImage.setImageDrawable(tmpDraw);

		return tempImage;
	}
	
	private void fillList(){//заполнение кешированых элементов
		int max = 3;
		if(ID_ARRAY_SIZE<3)
			max = ID_ARRAY_SIZE;
		for(int i = 0; i<max; i++){
			visibleImg.add(createImgView(getActivity(), i));
		}
	}
	
	private void refreshDisplay(boolean _rs){//перезагрузка отображения фрагмента - береться второй элимент в кеше
		cardShowdRL.removeAllViews();
		if(currElem<=1 && _rs){
			cardShowdRL.addView(visibleImg.get(0));
		}
		else
			cardShowdRL.addView(visibleImg.get(1));
		
		addButton();
		//Toast.makeText(getActivity(), "size " + visibleImg.size(), Toast.LENGTH_LONG).show();
	}

	
	//Работа с кэшем при продвижении по списку
	private void swipeRightDisplay(int _centerElem){
		if(currElem>1){
			visibleImg.add(0, createImgView(getActivity(), _centerElem-2));
		}
		if(currElem!=ID_ARRAY_SIZE-1){
			visibleImg.removeLast();
		}
		refreshDisplay(true);
	}
	
	private void swipeLeftDisplay(int _centerElem){
		if(currElem==0 && visibleImg.size() == 3)
		{}
		else{
			if(currElem != ID_ARRAY_SIZE-2 )
				visibleImg.add(createImgView(getActivity(), _centerElem+2));
			if(currElem!=0)
				visibleImg.removeFirst();
		}
		refreshDisplay(false);
	}
	
	private void addButton(){
		Button tmpBtn =  new Button(getActivity());
		tmpBtn.setText("Press it!");
		
		RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
		btnParams.topMargin = visibleImg.get(0).getDrawable().getIntrinsicHeight()-100;
		btnParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		tmpBtn.setLayoutParams(btnParams);
		
		tmpBtn.setOnClickListener(new OnClickListener() {//Возвращает номер выбранного элемента в отношении к списку
			
			@Override
			public void onClick(View arg0) {
				getFragmentManager().popBackStack();
				if(getActivity() instanceof Role_choosing)
					((Role_choosing) getActivity()).onGalleryClose(currElem);
			}
		});
		
		cardShowdRL.addView(tmpBtn);
	}
	
}