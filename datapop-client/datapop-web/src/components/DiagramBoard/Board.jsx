import React from 'react';
import { Stage, Layer } from 'react-konva';
import Shape from './Shape';

const Board = ({ objects, onDragMove }) => {
  return (
    <Stage width={window.innerWidth} height={window.innerHeight}>
      <Layer>
        {objects.map((object, index) => (
          <Shape
            key={index}
            shape={object}
            index={index}
            onDragMove={onDragMove}
          />
        ))}
      </Layer>
    </Stage>
  );
};

export default Board;
