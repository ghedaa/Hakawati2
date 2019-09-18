package com.example.youtube;

public class Video {
 
        private String name;
        private int view;
        private int thumbnail;

        public Video() {
        }

        public Video(String name, int view, int thumbnail) {
            this.name = name;
            this.view = view;
            this.thumbnail = thumbnail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getview() {
            return view;
        }

        public void setview(int view) {
            this.view = view;
        }

        public int getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(int description) {
            this.thumbnail = description;
        }
    }

