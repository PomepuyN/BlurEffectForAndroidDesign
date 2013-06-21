package com.npi.blureffect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Custom view used to render an horizontal part (slice) of an image.
 * 
 * @author Nicolas POMEPUY
 * 
 */
class ScrollableImageView extends View {

	// A bitmap adapted to the View size
	private Bitmap adaptedImage;
	// A Paint object used to render the image
	private Paint paint = new Paint();
	// The original Bitmap
	private Bitmap originalImage;
	// The screen width used to render the image
	private int screenWidth;
	private int scrollY = 0;

	public ScrollableImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ScrollableImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ScrollableImageView(Context context) {
		this(context, null);
	}

	/**
	 * Draws the view if the adapted image is not null
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		if (adaptedImage != null)
			canvas.drawBitmap(adaptedImage, 0, 0, paint);

	}

	/**
	 * Handle an external scroll and render the image by switching it by a
	 * distance
	 * 
	 * @param distY
	 *            the distance from the top
	 */
	public void handleScroll(float distY) {

		if (getHeight() > 0 && originalImage != null) {

			if (scrollY <= originalImage.getHeight() - getHeight()) {
				adaptedImage = Bitmap.createBitmap(originalImage, 0, (int) -distY, screenWidth, getHeight());

				invalidate();
			}
		}

	}

	public void setoriginalImage(Bitmap bmp) {
		this.originalImage = bmp;
		adaptedImage = Bitmap.createBitmap(bmp);
		invalidate();
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

}