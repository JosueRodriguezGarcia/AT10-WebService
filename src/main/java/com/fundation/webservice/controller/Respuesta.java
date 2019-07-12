package com.fundation.webservice.controller;

public class Respuesta {
        private String nombre;
        private String acodec;
        private String vcodec;
        private String path;

        public Respuesta (String nombre, String acodec, String vcodec, String path) {
            this.nombre = nombre;
            this.acodec = acodec;
            this.vcodec = vcodec;
            this.path = path;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getAcodec() {
            return acodec;
        }

        public void setAcodec(String acodec) {
            this.acodec = acodec;
        }

        public String getVcodec() {
            return vcodec;
        }

        public void setVcodec(String vcodec) {
            this.vcodec = vcodec;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
}
