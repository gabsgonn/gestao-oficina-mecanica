import { Routes } from '@angular/router';
import { Generic } from './generic/generic';
import { Modelos } from './modelos';
import { Home } from './home/home';
import { Field, VisaoGeral } from 'zouund';

export const routes: Routes = [
    { path: '', component: Home },
    /* ...Modelos.map(m => ({
        path: m.toLocaleLowerCase(),
        component: Generic,
        data: { Schema: m },
    })), */
    {
        path: 'pessoa', component: VisaoGeral,
        data: {
            fields: [
<<<<<<< HEAD
            {
                field: 'nomeCompleto',
                title: 'Nome Completo',
            },
            {
                field: 'cpf',
                title: 'CPF / CNPJ',
            },
            {
                field: 'Data Nascimento',
                title: 'Nome Completo',
            },
            {
                field: 'id',
                title: 'ID',
            },
=======
              {
                  field: 'id',
                  title: 'ID',
              },
              {
                  field: 'nome',
                  title: 'Nome',
              },
              {
                  field: 'cpf',
                  title: 'CPF / CNPJ',
              },
              {
                  field: 'email',
                  title: 'E-mail',
              },
              {
                  field: 'dataNascimento',
                  title: 'Data de Nascimento',
              },
            ] as Field[]
        }
    },
    {
        path: 'servico', component: VisaoGeral,
        data: {
            fields: [
              {
                  field: 'numero',
                  title: 'Numero',
              },
              {
                  field: 'descricao',
                  title: 'Descricao',
              },
              {
                  field: 'dataInicio',
                  title: 'Data Inicio',
              },
              {
                  field: 'dataFinalizacao',
                  title: 'Data Finalizacao',
              },
              {
                  field: 'valor',
                  title: 'valor',
              },
              {
                  field: 'veiculo',
                  title: 'veiculo',
              },
            ] as Field[]
        }
    },
    {
        path: 'servico', component: VisaoGeral,
        data: {
            fields: [
              {
                  field: 'placa',
                  title: 'Placa',
              },
              {
                  field: 'proprietario',
                  title: 'Proprietario',
              },
>>>>>>> ae66475a3e15bf138f7414c8b11e7496a98b6c0f
            ] as Field[]
        }
    }
];
