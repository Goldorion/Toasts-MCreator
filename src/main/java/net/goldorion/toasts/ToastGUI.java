package net.goldorion.toasts;

import net.mcreator.ui.MCreator;
import net.mcreator.ui.component.JColor;
import net.mcreator.ui.component.util.ComponentUtils;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.help.HelpUtils;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.minecraft.TextureComboBox;
import net.mcreator.ui.modgui.ModElementGUI;
import net.mcreator.ui.validation.ValidationGroup;
import net.mcreator.ui.validation.component.VTextField;
import net.mcreator.ui.validation.validators.TextFieldValidator;
import net.mcreator.ui.workspace.resources.TextureType;
import net.mcreator.workspace.elements.ModElement;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class ToastGUI extends ModElementGUI<Toast> {

    private final VTextField title = new VTextField(12);
    private final JColor titleColor = new JColor(mcreator, true, false);
    private final VTextField description = new VTextField(30);
    private final JColor descriptionColor = new JColor(mcreator, true, false);
    private final JCheckBox drawShadows = L10N.checkbox("elementgui.common.enable");
    private final JSpinner durationTime = new JSpinner(new SpinnerNumberModel(5000, 1000, 600000, 500));
    private final TextureComboBox icon = new TextureComboBox(mcreator, TextureType.SCREEN);
    private final TextureComboBox background = new TextureComboBox(mcreator, TextureType.SCREEN);

    private final ValidationGroup page1group = new ValidationGroup();

    public ToastGUI(MCreator mcreator, @Nonnull ModElement modElement, boolean editingMode) {
        super(mcreator, modElement, editingMode);
        this.initGUI();
        super.finalizeGUI();
    }

    @Override
    protected void initGUI() {
        ComponentUtils.deriveFont(title, 16.0F);
        ComponentUtils.deriveFont(description, 16.0F);

        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        mainPanel.setOpaque(false);

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/title"), L10N.label("elementgui.toast.title")));
        mainPanel.add(title);
        page1group.addValidationElement(title);
        title.setValidator(new TextFieldValidator(title, L10N.t("elementgui.toast.cant_be_empty", title)));
        title.enableRealtimeValidation();

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/title_color"), L10N.label("elementgui.toast.title_color")));
        mainPanel.add(titleColor);
        titleColor.setOpaque(false);

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/description"), L10N.label("elementgui.toast.description")));
        mainPanel.add(description);

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/description_color"), L10N.label("elementgui.toast.description_color")));
        mainPanel.add(descriptionColor);
        descriptionColor.setOpaque(false);

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/draw_shadows"), L10N.label("elementgui.toast.draw_shadows")));
        mainPanel.add(drawShadows);
        drawShadows.setOpaque(false);

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/duration_time"), L10N.label("elementgui.toast.duration_time")));
        mainPanel.add(durationTime);

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/icon"), L10N.label("elementgui.toast.icon")));
        mainPanel.add(icon);

        mainPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("toast/background"), L10N.label("elementgui.toast.background")));
        mainPanel.add(background);


        addPage(L10N.t("elementgui.common.page_mainPanel"), PanelUtils.totalCenterInPanel(mainPanel)).validate(page1group);
    }

    @Override
    public void reloadDataLists() {
        super.reloadDataLists();

        icon.reload();
        background.reload();
    }

    @Override
    protected void openInEditingMode(Toast toast) {
        title.setText(toast.title);
        description.setText(toast.description);
        durationTime.setValue(toast.durationTime);
        icon.setTextureFromTextureName(toast.icon);
        background.setTextureFromTextureName(toast.background);
        titleColor.setColor(toast.titleColor);
        descriptionColor.setColor(toast.descriptionColor);
        drawShadows.setSelected(toast.drawShadows);
    }

    @Override
    public Toast getElementFromGUI() {
        Toast toast = new Toast(modElement);
        toast.title = title.getText();
        toast.description = description.getText();
        toast.durationTime = (int) durationTime.getValue();
        toast.icon = icon.getTexture().getTextureName();
        toast.background = background.getTexture().getTextureName();
        toast.titleColor = titleColor.getColor();
        toast.descriptionColor = descriptionColor.getColor();
        toast.drawShadows = drawShadows.isSelected();

        return toast;
    }

    @Override
    public URI contextURL() {
        return null;
    }
}
