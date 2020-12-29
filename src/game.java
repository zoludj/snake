import * as chalk from "chalk";
import * as prompts from "prompts";
import { generateApple } from "./index";



// (async () => {
//   const queHeight = await prompts({
//     type: "number",
//     name: "height",
//     message: "Enter height",
//     validate: (height) => (height > 5 ? `width need more 5` : true),
//   });

//   const queWidth = await prompts({
//     type: "text",
//     name: "width",
//     message: "Enter width",
//     validate: (width) => (width > 5 ? `width need more 5` : true),
//   });

// })();
        export class Cell {
    x: number;
    y: number;

    constructor(x: number, y: number) {
        this.x = x;
        this.y = y;
    }
}
    export type Direction = "Up" | "Right" | "Left" | "Down";
        export const FRAMES_PER_SECOND = 2;

        const isInArray = (i: number, j: number, apples: Cell[],): boolean => {
        return apples.filter((it) => it.x === i && it.y === j).length > 0;
        };
        const move = (direction, snake): void => {
        let copySnake: Cell[] = [];
        snake.forEach((it) => copySnake.push(new Cell(it.x, it.y)));

        switch (direction) {
        case "Up":

        snake[0].x = snake[0].x === 0 ? 9: snake[0].x - 1
        break;
        case "Right":
        snake[0].y = snake[0].y === 19 ? 0: snake[0].y +1
        break;
        case "Left":
        snake[0].y = snake[0].y === 0 ? 19: snake[0].y -1
        break;
        case "Down":
        snake[0].x = snake[0].x === 9 ? 0: snake[0].x + 1
        break;
default:
        break;
        }
        for (let i = 1; i < snake.length; i++) {
        snake[i] = copySnake[i - 1];
        }
        };
        const action = (apples, snake): void => {
        const snakeBody: Cell[] = [];
        snake.filter((it, index) => index !== 0).forEach(element => snakeBody.push(element));
        if (isInArray(snake[0].x, snake[0].y, snakeBody)) {
        console.log('Game End');
        process.exit();
        }
        if (isInArray(snake[0].x, snake[0].y, apples)) {
        let founded: Cell | undefined = apples.find(
        (c) => c.x === snake[0].x && c.y === snake[0].y
        );
        if (founded !== undefined) {
        let index = apples.indexOf(founded);
        apples.splice(index, 1)
        generateApple(10, 20)
        apples.push(generateApple(10,20))
        }
        for (let i = 0; i < 3; i++) {
        let tail: Cell = snake[snake.length - 1];
        snake.push(new Cell(tail.x - 1, tail.y));
        }
        }
        };
        const printField = (apples, snake) => {
        for (let i = 0; i < 10; i++) {
        let row = "";
        for (let j = 0; j < 20; j++) {
        if (isInArray(i, j, apples)) {
        row += chalk.green("✿")
        } else if (isInArray(i, j, snake)) {
        row += chalk.hex('#FF8800').bold ("☻")
        } else {
        row +=chalk.black("_");
        }
        }

        console.log(row);
        }
        };
// define needed variables here so that they are available between redraws

// function will be called every (1000 / FRAMES_PER_SECOND) seconds
        export const draw = (direction: Direction, apples: Cell[], snake: Cell[]) => {
        console.clear();
        console.log(
        `Current direction: ${chalk.blue(direction)} (${chalk.gray(
        "try pressing arrows"
        )})`
        );
        console.log(chalk.red(`Score: ${Math.floor(Math.random() * 100)}`));

        move(direction, snake);
        action(apples, snake);

        printField(apples, snake);
        };

        import * as keypress from "keypress";
// import * as chalk from "chalk";
// import * as prompts from "prompts";
// import { Direction, FRAMES_PER_SECOND, draw } from "./game";

// (async () => {
//   const queHeight = await prompts({
//     type: "number",
//     name: "height",
//     message: "Enter height",
//     validate: (height) => (height > 5 ? `width need more 5` : true),
//   });

//   const queWidth = await prompts({
//     type: "text",
//     name: "width",
//     message: "Enter width",
//     validate: (width) => (width > 5 ? `width need more 5` : true),
//   });
//   start(() => square(queHeight.heigth, queWidth.width));
//   setInterval(() => draw, 1000 / FRAMES_PER_SECOND);
// })();

// function square(n) {
//   for (let i = 0; i < 10; i++) {
//     let row = "";
//     for (let j = 0; j < 20; j++) {
//       row += "*";
//     }
//     console.log(row);
//   }
// }
