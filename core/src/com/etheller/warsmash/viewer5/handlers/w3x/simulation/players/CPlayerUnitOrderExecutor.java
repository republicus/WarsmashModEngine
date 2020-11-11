package com.etheller.warsmash.viewer5.handlers.w3x.simulation.players;

import com.badlogic.gdx.math.Vector2;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CSimulation;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.CUnit;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.orders.COrderNoTarget;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.orders.COrderTargetPoint;
import com.etheller.warsmash.viewer5.handlers.w3x.simulation.orders.COrderTargetWidget;
import com.etheller.warsmash.viewer5.handlers.w3x.ui.command.CommandErrorListener;

public class CPlayerUnitOrderExecutor implements CPlayerUnitOrderListener {
	private final CSimulation game;
	private final CommandErrorListener errorListener;

	public CPlayerUnitOrderExecutor(final CSimulation game, final CommandErrorListener errorListener) {
		this.game = game;
		this.errorListener = errorListener;
	}

	@Override
	public void issueTargetOrder(final int unitHandleId, final int abilityHandleId, final int orderId,
			final int targetHandleId, final boolean queue) {
		final CUnit unit = this.game.getUnit(unitHandleId);
		unit.order(this.game, new COrderTargetWidget(abilityHandleId, orderId, targetHandleId), queue);
	}

	@Override
	public void issuePointOrder(final int unitHandleId, final int abilityHandleId, final int orderId, final float x,
			final float y, final boolean queue) {
		final CUnit unit = this.game.getUnit(unitHandleId);
		unit.order(this.game, new COrderTargetPoint(abilityHandleId, orderId, new Vector2(x, y)), queue);
	}

	@Override
	public void issueImmediateOrder(final int unitHandleId, final int abilityHandleId, final int orderId,
			final boolean queue) {
		final CUnit unit = this.game.getUnit(unitHandleId);
		unit.order(this.game, new COrderNoTarget(abilityHandleId, orderId), queue);
	}

}