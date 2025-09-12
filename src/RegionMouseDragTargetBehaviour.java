import com.almasb.fxgl.entity.Entity;

public class RegionMouseDragTargetBehaviour extends MouseDragTargetBehaviour {
  private Entity region;

  public RegionMouseDragTargetBehaviour() {}

  public RegionMouseDragTargetBehaviour(Entity region) {
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
    /*
      if card is dragged from one container to another AND if both containers are different
        REMOVE card from OLD container
        ADD card to NEW container
     */
    //var sourceContainer =
    var destinationContainer = region.getComponentOptional(CardContainerComponent.class);

    destinationContainer.ifPresent(CardContainerComponent::layoutCards);
    System.out.println("dragged into the honeydew");
    if (!draggedEntity.hasComponent(RegionMouseDragTargetBehaviour.class)) {
      //card enters region
      draggedEntity.addComponent(new RegionMouseDragTargetBehaviour(region));
      destinationContainer.ifPresent(c -> {
        c.addCard(draggedEntity);
      });
    }
  }
}
