package net.runelite.client.plugins.agility;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;

@AllArgsConstructor
enum DorgeshKaanPart
{
	POWERBOX(ItemID.POWERBOX, "powerbox"),
	LEVER(ItemID.LEVER_10991, "lever"),
	COG(ItemID.COG, "cog"),
	CAPACITOR(ItemID.CAPACITOR, "capacitor"),
	FUSE(ItemID.FUSE_10985, "fuse"),
	METER(ItemID.METER, "meter");

	@Getter
	private int itemId;

	@Getter
	private String name;

	private static final Map<String, DorgeshKaanPart> PARTS;

	static
	{
		ImmutableMap.Builder<String, DorgeshKaanPart> builder = new ImmutableMap.Builder<>();

		for (DorgeshKaanPart part : values())
		{
			builder.put(part.name, part);
		}

		PARTS = builder.build();
	}

	static DorgeshKaanPart findPart(String name)
	{
		return PARTS.get(name);
	}
}
