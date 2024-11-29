import React from 'react';
import { Group, Rect, Text } from 'react-konva';

const Shape = ({ shape, index, onDragMove }) => {
  return (
    <Group
      draggable
      x={shape.x}
      y={shape.y}
      onDragMove={(e) => onDragMove(e, index)}
    >
      <Rect
        width={150}
        height={50}
        fill="#282c34"
        cornerRadius={10}
        stroke="#404040"
        strokeWidth={2}
      />
      <Text
        text={shape.name}
        fontSize={18}
        fontFamily="monospace"
        fill="#dcdcdc"
        align="center"
        verticalAlign="middle"
        width={150}
        height={50}
        padding={10}
      />
    </Group>
  );
};

export default Shape;
