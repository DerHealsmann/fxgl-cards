import com.almasb.fxgl.entity.Entity;

public class CardContainerMouseDragTargetBehaviour extends MouseDragTargetBehaviour {
  private Entity region;

  public CardContainerMouseDragTargetBehaviour() {
  }

  public CardContainerMouseDragTargetBehaviour(Entity region) {
    this.region = region;
  }

  @Override
  public void onAdded() {
    super.onAdded();
    if (region == null) {
      this.region = entity;
    }
  }

  @Override
  public void onDragReleased(Entity draggedEntity) {
    // todo: add check around sourceContainer being assumed non-null
    var sourceContainer = CardContainerUtil.getSourceContainer(draggedEntity);
    var destinationContainer = region.getComponentOptional(CardContainerComponent.class);

    if (sourceContainer.isPresent() && destinationContainer.isPresent() && sourceContainer.get() != destinationContainer.get()) {
      System.out.println("dragged into the honeydew");
      sourceContainer.get().removeCard(draggedEntity);
      destinationContainer.get().addCard(draggedEntity);

      draggedEntity.removeComponent(CardContainerMouseDragTargetBehaviour.class);
      draggedEntity.addComponent(new CardContainerMouseDragTargetBehaviour(region));
    }
  }
}
