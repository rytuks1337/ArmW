package com.rytis.armw.ui.bracket;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rytis.armw.R;

import java.util.ArrayList;

public class BracketView extends LinearLayout {
    private final ArrayList<BracketMatchModel> matches;

    public BracketView(Context context, ArrayList<BracketMatchModel> matches) {
        super(context);
        this.matches = matches;
        setOrientation(VERTICAL);
        init();
    }

    public BracketView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.matches = new ArrayList<>();
    }

    private void init() {
        for (BracketMatchModel match : matches) {
            View matchView = inflate(getContext(), R.layout.matchvs_item, null);
            // Populate matchView with data (similar to RecyclerView.Adapter logic)
            TextView player1 = matchView.findViewById(R.id.text_player1);
            TextView player2 = matchView.findViewById(R.id.text_player2);
            TextView winner = matchView.findViewById(R.id.text_winner);

            player1.setText(match.getPlayer1());
            player2.setText(match.getPlayer2());
            winner.setText("Winner: " + match.getWinner());

            addView(matchView);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        Paint linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.purple_500));
        linePaint.setStrokeWidth(4f);

        // Draw connecting lines
        for (int i = 0; i < getChildCount() - 1; i++) {
            View match1 = getChildAt(i);
            View match2 = getChildAt(i + 1);

            float startX = match1.getRight();
            float startY = match1.getTop() + (match1.getHeight() / 2f);

            float endX = match2.getLeft();
            float endY = match2.getTop() + (match2.getHeight() / 2f);

            canvas.drawLine(startX, startY, endX, endY, linePaint);
        }
    }
}
