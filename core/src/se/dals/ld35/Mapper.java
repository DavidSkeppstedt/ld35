package se.dals.ld35;

import com.badlogic.ashley.core.ComponentMapper;
import se.dals.ld35.components.*;

/**
 * Created by david on 2016-04-16.
 */
public class Mapper {
    public static final ComponentMapper<VisualComponent> VISUAL = ComponentMapper.getFor(VisualComponent.class);
    public static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<SizeComponent> SIZE = ComponentMapper.getFor(SizeComponent.class);
    public static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<JumpComponent> JUMP = ComponentMapper.getFor(JumpComponent.class);
}
