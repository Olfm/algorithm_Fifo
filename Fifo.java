public class Fifo {
    public static void main(String[] args) {
        int memory = 4; // объем области замещения оперативной памяти
        int numberPages = 16; // количество различных страниц
        Page[] pages = new Page[numberPages];

        for (int i = 0; i < numberPages; i++) {
            pages[i] = new Page("Cтраница " + i); // заполняем массив страцами — 16 штук
        }

        Page[] wait = new Page[pages.length - memory]; // массив со страницами, которые будут выгружены

        if (memory < pages.length) {
            System.arraycopy(pages, memory, wait, 0, pages.length - memory); // переводим в "спящий режим" сраницы, которые не попали в объем памяти (с конца)
            System.out.println("Число страничных прерываний " + wait.length);
            System.out.println();


            for (int x = memory; x < (pages.length - 1); x++) {
                pages[x] = null;
            }
        }

        for (int i = 0; i < wait.length; i++) {
            System.out.println(wait[i].name + " выгружена из памяти");
        }
        System.out.println();


        int counterWait = wait.length;
        int pageStoped = 0;


        for (int i = memory; i >= 0; i --) {
            switch (i) {
                case 4:
                    System.out.println("Нет свободной памяти");
                    System.out.println();
                    break;
                case 3:
                case 2:
                case 1:
                    if (counterWait > 0) {
                        System.out.println("Занято 3 из 4, добавили страницу из очереди");
                        System.arraycopy(wait, pageStoped, pages, 3, 1); // добавляем в конец одну страницу из спящих страниц — от первой к последней
                        pageStoped++;
                        counterWait--;
                        i++;
                    }

                    break;

                case 0:
                    System.out.println();
                    System.out.println(pages[0].name + " обработана и удалена");
                    System.out.println();
                    System.out.println("Все станицы выполнены, память свободна");
                    break;
            }

            for (int k = 3; k > 0; k--) {
                pages[k-1] = pages[k];
            }

        }

    }
}
