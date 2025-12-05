class FlashcardApp:
    def __init__(self, interactive=True):
        self.flashcards = [] #хранение кортежей
        self.interactive = interactive # нужно для тестов, сохраняет состояние пользователем или тестами вызвана

    def add_flashcard(self, word, translation): # добавление новой карточки
        self.flashcards.append((word, translation)) # добавляем в конец кортежа новую карточку
        print(f"Карточка '{word}' -> '{translation}' добавлена.") 

    def update_flashcard(self, word, new_translation): # обновление перевода
        for i, (w, t) in enumerate(self.flashcards): # распаковка карточки
            if w == word:
                self.flashcards[i] = (word, new_translation)
                print(f"Перевод для '{word}' обновлён на '{new_translation}'.")
                return # выходим из функции сразу после нахождения и обновления
        print(f"Карточка '{word}' не найдена.")

    def start_session(self, return_result=False):
        if not self.flashcards:
            print("Нет карточек для тренировки.")
            if return_result:
                return {"correct": 0, "wrong": 0}
            return
        correct_count = 0
        print("\nНачинаем сессию! Введите перевод для каждого слова:")

        for word, correct_translation in self.flashcards:
            answer = input(f"{word} -> ")
            if answer.strip().lower() == correct_translation.lower():
                print("Верно!")
                correct_count += 1
            else:
                print(f"Неверно. Правильный ответ: {correct_translation}")

        wrong_count = len(self.flashcards) - correct_count
        print(f"\nСессия завершена. Правильных: {correct_count}, Неправильных: {wrong_count}")

        if return_result:
            return {"correct": correct_count, "wrong": wrong_count}

    def show_flashcards(self):
        if not self.flashcards:
            print("Карточек нет.")
            return
        print("\nСписок карточек:")
        for w, t in self.flashcards:
            print(f"{w} -> {t}")

    def start_menu(self):
        if not self.interactive:
            return  # отключаем меню для тестов
        while True:
            print("\n--- Flashcard App ---")
            print("1. Добавить карточку")
            print("2. Обновить карточку")
            print("3. Показать все карточки")
            print("4. Провести тренировку")
            print("5. Выход")
            choice = input("Выберите действие: ").strip()
            if choice == "1":
                word = input("Введите слово: ").strip()
                translation = input("Введите перевод: ").strip()
                self.add_flashcard(word, translation)
            elif choice == "2":
                word = input("Введите слово для обновления: ").strip()
                new_translation = input("Введите новый перевод: ").strip()
                self.update_flashcard(word, new_translation)
            elif choice == "3":
                self.show_flashcards()
            elif choice == "4":
                self.start_session()
            elif choice == "5":
                print("Выход из программы. Пока!")
                break
            else:
                print("Неверный выбор. Попробуйте снова.")

if __name__ == "__main__":
    app = FlashcardApp()
    app.start_menu()
