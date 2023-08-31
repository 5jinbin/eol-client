<!-- 文件路径：src\components\WordBanner.vue -->


<template>
    <div class="word-display">
      <div class="word-pronunciation">{{ pronunciation }}</div>
      <div class="word-spelling">{{ spelling }}</div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      pronunciation: {
        type: String,
        required: true
      },
      spelling: {
        type: String,
        required: true
      }
    }
  }
  </script>
  
  <style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Kalam&family=Spectral:ital,wght@1,200&display=swap');

  .word-display {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 100%;

  text-align: center;
  }
  
  .word-pronunciation {
    font-family: 'Spectral', serif;
    font-size: 24px;
    color: darkgrey;
    margin-bottom: 20px;
  }
  
  .word-spelling {
    font-family: 'Kalam', cursive;
    font-size: 124px;
    text-align: center;
  }
  </style>