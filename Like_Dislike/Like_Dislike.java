/*
Like_Dislike

Logic:
- 좋아요 버튼이 눌리면, 좋아요 버튼이 눌린 상태로 변한 후, 좋아요가 1 증가한다.
- 좋아요 버튼이 눌린 상태에서 다시 눌리면 좋아요가 눌리지 않은 상태로 변한 후, 좋아요가 1 감소한다. (좋아요 취소)

- 싫어요 버튼이 눌리면, 싫어요 버튼이 눌린 상태로 변한 후, 싫어요가 1 증가한다.
- 싫어요 버튼이 눌린 상태에서 다시 눌리면 싫어요가 눌리지 않은 상태로 변한 후, 싫어요가 1 감소한다. (싫어요 취소)

- 좋아요 버튼과 싫어요 버튼은 동시에 눌려 있는 상태가 될 수 없다.
    - 좋아요 버튼이 눌릴 시, 싫어요 버튼이 눌려있는 상태라면 눌려있지 않은 상태로 변하고, 싫어요가 1 감소한다.
    - 싫어요 버튼이 눌릴 시, 좋아요 버튼이 눌려있는 상태라면, 눌려있지 않은 상태로 변한 후, 좋아요가 1 감소한다.
*/

package com.example.project_movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button likeButton, dislikeButton; //Button : 좋아요, 싫어요 버튼
    TextView likeCount,dislikeCount; // TextView: 좋아요, 싫어요 숫자

    int likeNum = 15; //현재 좋아요 개수
    int dislikeNum = 1; //현재 싫어요 개수

    Boolean likeState, dislikeState = false; //현재 좋아요, 싫어요 상태 (모두 눌리지 않은 것으로 초기화)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeButton = findViewById(R.id.LikeButton);
        dislikeButton = findViewById(R.id.DislikeButton);

        likeCount = findViewById(R.id.LikeCountView);
        dislikeCount = findViewById(R.id.DislikeCountView);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //좋아요 버튼이 눌릴 시, Like()함수가 실행됨
                Like();
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //싫어요 버튼이 눌릴 시, Dislike()함수가 실행됨
                Dislike();
            }
        });
    }


    public void Like() {

        if(!likeState){//Case 1: 싫어요가 눌리지 않은 상태에서 좋아요가 눌릴 시
            IncrLike();
            if (dislikeState) { //Case 2: 싫어요가 눌린 상태에서 좋아요가 눌릴 시
                DecrDis();
            }
        } else { //Case 3: 좋아요가 다시 눌릴 시 (좋아요 취소)
            DecrLike();
        }
    }

    public void Dislike() {

        if(!dislikeState){// Case 4: 좋아요가 눌리지 않은 상태에서 싫어요가 눌렸을 시
            IncrDis();
            if(likeState){ // Case 5: 좋아요가 눌린 상태에서 싫어요가 눌렸을 시
                DecrLike();
            }
        } else { // Case 6: 싫어요가 다시 눌릴 시 (싫어요 취소)
            DecrDis();
        }
    }

    public void IncrLike(){ //Like 증가
        likeNum += 1;
        likeCount.setText(String.valueOf(likeNum));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);
        likeState = !likeState;
    }

    public void DecrLike(){ //Like 감소
        likeNum -= 1;
        likeCount.setText(String.valueOf(likeNum));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up);
        likeState = !likeState;
    }

    public void IncrDis(){ //Dislike 증가
        dislikeNum += 1;
        dislikeCount.setText(String.valueOf(dislikeNum));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
        dislikeState = !dislikeState;
    }

    public void DecrDis(){ //Dislike 감소
        dislikeNum -= 1;
        dislikeCount.setText(String.valueOf(dislikeNum));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down);
        dislikeState = !dislikeState;
    }

}
