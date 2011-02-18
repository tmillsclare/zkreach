package org.zkoss.reach.android.components;

import java.io.IOException;

import org.zkoss.reach.android.impl.ReachComponent;
import org.zkoss.zk.ui.sys.ContentRenderer;

public class LinearLayout extends ReachComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4044763663196042378L;
	
	private int _width;
	private int _height;
	private float _weightSum;
	
	
	
	public int getWidth() {
		return _width;
	}



	public void setWidth(int width) {
		if(width != _width) { 
			this._width = width;
			smartUpdate("LayoutParams", renderLayoutParams());
		}
	}


	public int getHeight() {
		return _height;
	}



	public void setHeight(int height) {
		if(height != _height) {		
			this._height = height;
			smartUpdate("LayoutParams", renderLayoutParams());
		}
	}



	public float getWeightSum() {
		return _weightSum;
	}



	public void setWeightSum(float weightSum) {
		
		if(weightSum != _weightSum) {
			this._weightSum = weightSum;
			smartUpdate("WeightSum", _weightSum);
		}
	}
	
	private String renderLayoutParams() {
		StringBuilder sb = new StringBuilder();
		sb.append(_width);
		sb.append(',');
		sb.append(_height);
		
		return sb.toString();
	}



	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.AbstractComponent#renderProperties(org.zkoss.zk.ui.sys.ContentRenderer)
	 */
	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {
		
		super.renderProperties(renderer);
		render(renderer, "LayoutParams", renderLayoutParams());
	}
}
