package com.example.newsapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.databinding.ItemNewsBinding;
import com.example.newsapp.model.Article;
import com.example.newsapp.model.NewsResponse;
import com.bumptech.glide.Glide;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemNewsViewHolder> {

    private NewsResponse newsResponses;

    public NewsAdapter(NewsResponse news) {
        this.newsResponses = news;
    }



    @NonNull
    @Override
    public ItemNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemNewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewsViewHolder holder, int position) {
        holder.bind(newsResponses.getArticles().get(position));
    }

    @Override
    public int getItemCount() {
        return newsResponses.articles.size();
    }

    public class ItemNewsViewHolder extends RecyclerView.ViewHolder {

        private ItemNewsBinding binding;

        public ItemNewsViewHolder(ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Article article) {
            // Set data ke tampilan berdasarkan objek NewsResponse.Article
            binding.tvTitle.setText(article.getTitle());
            binding.tvAuthor.setText(article.getAuthor());
            binding.tvDescription.setText(article.getDescription());

            // Set gambar menggunakan Glide atau library lainnya (Glide contohnya)
            Glide.with(itemView.getContext())
                    .load(article.getUrlToImage())
                    .placeholder(R.drawable.ic_launcher_background) // Ganti dengan gambar placeholder sesuai kebutuhan
                    .into(binding.imgNews);
        }
    }
}