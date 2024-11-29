import React, { useState } from 'react';
import Board from '../../components/DiagramBoard';
import Editor from '../../components/Editor';
import './Datapop.module.css';

const Datapop = () => {
  const [objects, setObjects] = useState([]);


  const handleAddObject = (name) => {
    const newObject = {
      name,
      x: Math.random() * window.innerWidth,
      y: Math.random() * window.innerHeight,
    };
    setObjects([...objects, newObject]);
  };

  const handleDragMove = (e, index) => {
    const updatedObjects = [...objects];
    updatedObjects[index] = {
      ...updatedObjects[index],
      x: e.target.x(),
      y: e.target.y(),
    };
    setObjects(updatedObjects);
  };

  return (
    <div className="datapop-layout">
      <div className="editor-container">
        <Editor onAddObject={handleAddObject} />
      </div>
      <div className="board-container">
        <Board objects={objects} onDragMove={handleDragMove} />
      </div>
    </div>
  );
};

export default Datapop;
