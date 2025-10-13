<#--
 # This file is part of Toasts-MCreator.
 # Copyright (C) 2024-2025, Goldorion, opensource contributors
 #
 # Permission is hereby granted, free of charge, to any person obtaining a copy
 # of this software and associated documentation files (the "Software"), to deal
 # in the Software without restriction, including without limitation the rights
 # to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 # copies of the Software, and to permit persons to whom the Software is
 # furnished to do so, subject to the following conditions:
 #
 # Toasts-MCreator is distributed in the hope that it will be useful,
 # but WITHOUT ANY WARRANTY; without even the implied warranty of
 # MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 # GNU General Public License for more details.
 #
 # The above copyright notice and this permission notice shall be included in all
 # copies or substantial portions of the Software.
-->
<#compress>
package ${package}.client.toasts;

public class ${name}Toast implements Toast {
	private Toast.Visibility wantedVisibility = Toast.Visibility.HIDE;
	private static final ResourceLocation BACKGROUND_SPRITE = <#if !data.background?has_content>ResourceLocation.withDefaultNamespace("toast/recipe")
															  <#else>ResourceLocation.parse("${modid}:textures/screens/${data.background}")
															  </#if>;
	<#if data.icon != "None">
	private static final ResourceLocation ICON_SPRITE = ResourceLocation.parse("toast:textures/screens/icon.png");
	</#if>
	
	private static final Component TITLE_TEXT = Component.translatable("toasts.${modid}.${registryname}.title");
	private static final Component DESCRIPTION_TEXT = Component.translatable("toasts.${modid}.${registryname}.description");

	private static final int DEFAULT_TIME = ${data.durationTime};
	private final double displayTime;
    private final ResourceLocation icon;

    public ${name}Toast() {
        this(DEFAULT_TIME, ICON_SPRITE);
    }

    public ${name}Toast(double time) {
        this(time, ICON_SPRITE);
    }

    public ${name}Toast(double displayTime, ResourceLocation icon) {
        this.displayTime = displayTime;
        this.icon = icon;
    }
	
	@Override
	public @NotNull Visibility getWantedVisibility() {
		return wantedVisibility;
	}
	
	@Override
	public void update(@NotNull ToastManager toastManager, long lastChanged) {
		double totalMS = (double) displayTime * toastManager.getNotificationDisplayTimeMultiplier();
		this.wantedVisibility = (double) lastChanged <= totalMS ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
	}
	
	@Override
	public void render(GuiGraphics guiGraphics, @NotNull Font font, long l) {
		guiGraphics.blitSprite(RenderType::guiTextured, BACKGROUND_SPRITE, 0, 0, this.width(), this.height());
		<#if data.icon != "None">
		guiGraphics.blit(RenderType::guiTextured, icon, 6, 6, 0, 0, 20, 20, 20, 20);
		</#if>
		guiGraphics.drawString(font, TITLE_TEXT, 30, 7, ${data.titleColor?has_content?then(data.titleColor.getRGB(), -11534256)}, ${data.drawShadows});
		guiGraphics.drawString(font, DESCRIPTION_TEXT, 30, 18, ${data.descriptionColor?has_content?then(data.descriptionColor.getRGB(), -16777216)}, ${data.drawShadows});
	}
}
</#compress>