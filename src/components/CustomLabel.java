package components;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    public enum TypeLabel {
        TITRE, DEFAULT, EN_TETE, SOUSTITRE, ERREUR, SUCCES, INFO
    }

    public CustomLabel(String text, TypeLabel type) {
        super(text);
        setLabelStyle(type);
    }

    private void setLabelStyle(TypeLabel type) {
        switch (type) {
            case TITRE:
                setFont(new Font("Arial", Font.BOLD, 28));
                setForeground(new Color(0x1D3557));
                setHorizontalAlignment(SwingConstants.CENTER);
                setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
                break;
            case EN_TETE:
                setFont(new Font("Arial", Font.BOLD, 20));
                setForeground(new Color(0x6C757D));
                setHorizontalAlignment(SwingConstants.LEFT);
                setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                break;
            case SOUSTITRE:
                setFont(new Font("Arial", Font.ITALIC, 18));
                setForeground(new Color(0xA6B1B7));
                setHorizontalAlignment(SwingConstants.LEFT);
                break;
            case ERREUR:
                setFont(new Font("Arial", Font.PLAIN, 16));
                setForeground(new Color(0xD32F2F));
                setHorizontalAlignment(SwingConstants.LEFT);
                break;
            case SUCCES:
                setFont(new Font("Arial", Font.PLAIN, 16));
                setForeground(new Color(0x388E3C));
                setHorizontalAlignment(SwingConstants.LEFT);
                break;
            case INFO:
                setFont(new Font("Arial", Font.PLAIN, 16));
                setForeground(new Color(0x0288D1));
                setHorizontalAlignment(SwingConstants.LEFT);
                break;
            default:
                setFont(new Font("Arial", Font.PLAIN, 14));
                setForeground(new Color(0x6C757D));
                setHorizontalAlignment(SwingConstants.LEFT);
                break;
        }
    }
}
