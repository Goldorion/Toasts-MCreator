package net.goldorion.toasts;

import net.mcreator.element.GeneratableElement;
import net.mcreator.workspace.elements.ModElement;

import java.awt.*;

public class Toast extends GeneratableElement {

    public String title, description, icon, background;
    public int durationTime;
    public Color titleColor, descriptionColor;
    public boolean drawShadows;

    public Toast(ModElement element) {
        super(element);
    }
}