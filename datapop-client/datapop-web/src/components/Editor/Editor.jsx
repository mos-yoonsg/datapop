import React, { useState } from 'react';

const Editor = ({ onAddObject }) => {
  const [inputText, setInputText] = useState("");

  const handleInputChange = (e) => {
    setInputText(e.target.value);
  };

  const handleAddClick = () => {
    if (inputText.trim()) {
      onAddObject(inputText);
      setInputText("");
    }
  };

  return (
    <div>
      <input
        type="text"
        value={inputText}
        onChange={handleInputChange}
        placeholder="Enter object name"
      />
      <button onClick={handleAddClick}>Add Object</button>
    </div>
  );
};

export default Editor;
