package ${package}.client.toasts;

public class ${name}Toast implements Toast {

	@Override
	public Visibility render(PoseStack poseStack, ToastComponent component, long lastChanged) {
		RenderSystem.setShaderTexture(0, TEXTURE);
		GuiComponent.blit(poseStack, 0, 0, 0, 32, this.width(), this.height());
		component.getMinecraft().font.draw(poseStack, Component.translatable("toasts.${modid}.${registryname}.title"), 30, 7, -11534256);
		component.getMinecraft().font.draw(poseStack, Component.translatable("toasts.${modid}.${registryname}.description"), 30, 18, -16777216);

		RenderSystem.enableBlend();
		RenderSystem.setShaderTexture(0, new ResourceLocation("${modid}:textures/screens/${data.icon}"));
		GuiComponent.blit(poseStack, 6, 6, 296, 120, 20, 20);

		if (lastChanged <= ${data.durationTime})
			return Visibility.SHOW;
		else
			return Visibility.HIDE;
	}
}
