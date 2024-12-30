package modelo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Pais {
    @SerializedName("name")
    private Nome nome;

    @SerializedName("region")
    private String regiao;

    @SerializedName("population")
    private long populacao;

    @SerializedName("capital")
    private List<String> capital;

    @SerializedName("flags")
    private Bandeira bandeira;

    // Getters e Setters
    public Nome getNome() {
        return nome;
    }

    public void setNome(Nome nome) {
        this.nome = nome;
    }

    public String getRegion() {
        return regiao;
    }

    public void setRegion(String regiao) {
        this.regiao = regiao;
    }

    public long getPopulation() {
        return populacao;
    }

    public void setPopulation(long populacao) {
        this.populacao = populacao;
    }

    public List<String> getCapital() {
        return capital;
    }

    public void setCapital(List<String> capital) {
        this.capital = capital;
    }

    public Bandeira getFlags() {
        return bandeira;
    }

    public void setFlags(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public static class Nome {
        @SerializedName("common")
        private String comum;

        @SerializedName("official")
        private String oficial;

        // Getters e Setters
        public String getComum() {
            return comum;
        }

        public void setComum(String comum) {
            this.comum = comum;
        }

        public String getOficial() {
            return oficial;
        }

        public void setOficial(String oficial) {
            this.oficial = oficial;
        }
    }

    public static class Bandeira {
        @SerializedName("png")
        private String png;

        // Getters e Setters
        public String getPng() {
            return png;
        }

        public void setPng(String png) {
            this.png = png;
        }
    }
}
