
import * as keypress from "keypress";
import * as chalk from "chalk";
import { Direction, FRAMES_PER_SECOND, draw, Cell } from "./game";

        keypress(process.stdin);
        process.stdin.setRawMode(true);

        const getRandomCell = (width: number, height: number): Cell => {
        return new Cell(getRandomInt(width), getRandomInt(height));
        };

        const getRandomInt = (max: number) => {
        return Math.floor(Math.random() * Math.floor(max));
        };

        export const generateApple = (width: number, height: number): Cell => {
        return getRandomCell(width, height);
        };

        const generateSnake = (): Cell[] => {
        return [new Cell(0, 0), new Cell(1, 0),new Cell(2, 0), new Cell(3, 0),new Cell(4, 0)];
        };

        const start = (draw: (direction: Direction, generateApple, generateSnake) => void
        ) => {
        // prompts is blocking ctrl+c event, so let's listen for it manually
        process.stdin.on("keypress", (str, key) => {
        if (key.ctrl && key.name === "c") {
        console.log(chalk.yellow("Bye!"));
        process.exit();
        }
        });

        const snake: Cell[] = generateSnake();

        let direction: Direction = "Right";

        process.stdin.on("keypress", function (ch, key) {
        switch (key.name) {
        case "up":
        direction = "Up";

        break;
        case "right":
        direction = "Right";

        break;
        case "left":
        direction = "Left";

        break;
        case "down":
        direction = "Down";
        break;
default:
        break;
        }
        });

        const apples: Cell[] = [];
        while (apples.length < 3) {
        apples.push(generateApple(10, 20));
        }

        setInterval(() => draw(direction, apples, snake), 1000 / FRAMES_PER_SECOND);
        };

        start(draw);