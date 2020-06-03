/*
 * Copyright (c) 2020, Plondrein <plondrein@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.agility;

import com.google.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.ComponentConstants;
import net.runelite.client.ui.overlay.components.LayoutableRenderableEntity;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

public class DorgeshKaanPartsOverlay extends Overlay
{
	private final AgilityPlugin plugin;
	private final AgilityConfig config;

	private final PanelComponent panelComponent = new PanelComponent();

	@Inject
	public DorgeshKaanPartsOverlay(AgilityPlugin plugin, AgilityConfig config)
	{
		setPosition(OverlayPosition.TOP_LEFT);
		setPriority(OverlayPriority.LOW);
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (!config.showDorgeshKaanParts() || isAtDorgeshKaanCouse())
		{
			return null;
		}

		String heavyPart = plugin.getCurrentHeavyPart();
		String lightPart = plugin.getCurrentLightPart();

		List<LayoutableRenderableEntity> children = panelComponent.getChildren();

		children.clear();
		panelComponent.setPreferredSize(new Dimension(200, panelComponent.getPreferredSize().height));
		panelComponent.setBackgroundColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);

		children.add(LineComponent.builder()
			.left("Heavy part (Agility):").leftColor(Color.WHITE)
			.right(heavyPart).rightColor(Color.GREEN)
			.build());

		children.add(LineComponent.builder()
			.left("Light part (Ranged):").leftColor(Color.WHITE)
			.right(lightPart).rightColor(Color.GREEN)
			.build());

		return panelComponent.render(graphics);
	}

	private boolean isAtDorgeshKaanCouse()
	{
		Player player = plugin.getClient().getLocalPlayer();
		if (player == null)
		{
			return false;
		}

		return player.getWorldLocation().getRegionID() != Courses.DORGESH_KAAN_AGILITY.getRegionId();
	}
}
