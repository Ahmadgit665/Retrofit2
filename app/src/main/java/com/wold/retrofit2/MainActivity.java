package com.wold.retrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResults;
    private JsonPlaceholderApi jsonPlaceholderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResults=findViewById(R.id.textview_text);

        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

         jsonPlaceholderApi= retrofit.create(JsonPlaceholderApi.class);

        // getposts();
       // getcomment();
         CreatePost();
    }
         public void getposts() {
            Call<List<Post>> call = jsonPlaceholderApi.getposts();

            call.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                    if (!response.isSuccessful()) {
                        textViewResults.setText("Code:" + response.code());
                        return;
                    }
                    List<Post> posts = response.body();
                    for (Post post : posts) {
                        String content = "";
                        content += "ID:" + post.getId() + "\n";
                        content += "User ID:" + post.getUserId() + "\n";
                        content += "Title:" + post.getTitle() + "\n";
                        content += "Text:" + post.getText() + "\n\n";
                        textViewResults.append(content);
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {

                    textViewResults.setText(t.getMessage());
                }
            });
        }

    public void getcomment() {
        Call<List<Comment>> call = jsonPlaceholderApi.getcomments();

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textViewResults.setText("Code:" + response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for (Comment comment : comments) {
                    String content = "";
                    content += "POST ID:" + comment.getPostId() + "\n";
                    content += " ID:" + comment.getId() + "\n";
                    content += "Name:" + comment.getName() + "\n";
                    content += "Text:" + comment.getText() + "\n";
                    content += "Email:" + comment.getEmail() + "\n\n";
                    textViewResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                textViewResults.setText(t.getMessage());
            }
        });
    }

        public void CreatePost(){
        Post post=new Post(23,"Title of Product","Email");

        Call<Post> call= jsonPlaceholderApi.Createposts(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResults.setText("Code:" + response.code());
                    return;
                }
                Post postResponse=response.body();
                String content = "";
                content += "Code:" + response.code() + "\n";
                content += "ID:" + postResponse.getId() + "\n";
                content += "User ID:" + postResponse.getUserId() + "\n";
                content += "Title:" + postResponse.getTitle() + "\n";
                content += "Text:" + postResponse.getText() + "\n\n";
                textViewResults.setText(content);
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });
        }
    }